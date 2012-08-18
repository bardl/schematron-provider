package com.pagero.schematron;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class SchematronProviderFactory {
    private static SchematronProviderFactory factory;

    public static SchematronProviderFactory newInstance() {
        if (factory == null) {
            factory = new SchematronProviderFactory();
        }
        return factory;
    }

    public SchematronProvider createSchematronProvider(String providerId) throws NoSchematronProviderFoundException {
        ServiceLoader<SchematronProvider> schematronProviders = getRegisteredSchematronProviders();
        for (SchematronProvider schematronProvider : schematronProviders) {
            if (schematronProvider.getId().equals(providerId)) {
                return schematronProvider;
            }
        }
        throw new NoSchematronProviderFoundException("No SchematronProvider was found matching [" + providerId + "]. " +
                "The following SchematronProviders was found [" + writeSchematronProvidersToString(schematronProviders) +  "].");
    }

    public List<SchematronProvider> getSchematronProviders() {
        ServiceLoader<SchematronProvider> ldr = getRegisteredSchematronProviders();
        List<SchematronProvider> result = copySchematronProvidersToList(ldr);
        return result;
    }

    protected SchematronProviderFactory() {}

    private String writeSchematronProvidersToString(ServiceLoader<SchematronProvider> schematronProviders) {
        StringBuilder providers = new StringBuilder();
        for (SchematronProvider schematronProvider : schematronProviders) {
            providers.append(schematronProvider);
            if (schematronProviders.iterator().hasNext()) {
                providers.append(", ");
            }
        }
        return providers.toString();
    }

    private List<SchematronProvider> copySchematronProvidersToList(ServiceLoader<SchematronProvider> ldr) {
        List<SchematronProvider> result = new ArrayList<SchematronProvider>();
        for (SchematronProvider provider : ldr) {
            result.add(provider);
        }
        return result;
    }

    private ServiceLoader<SchematronProvider> getRegisteredSchematronProviders() {
        ServiceLoader<SchematronProvider> ldr = ServiceLoader.load(SchematronProvider.class);
        if (!ldr.iterator().hasNext()) {
            throw new Error ("No SchematronProviders registered");
        }
        return ldr;
    }
}
