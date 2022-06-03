package com.company;

public enum Enfermedades {
    BRONQUITIS("Bronquitis Aguda"), RESFRIO("Resfriado Común"), OIDO("Dolor de oído"), INFLUENZA("Influenza"), SINUSITIS("Sinusitis"),
    INFECCION_PIEL("Infección en la piel"), DOLOR_GARGANTA("Dolor de Garganta"), INFECCION_URINARIA("Infección Urinaria");

    private String enfermedad;

    Enfermedades(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
}
