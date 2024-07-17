package test;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class TimeConversion {
    public static void main(String[] args) {
        // Obtener la fecha y hora actual en UTC
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println("Hora UTC: " + nowUTC);

        // Convertir a la hora local de Puebla, México
        ZonedDateTime nowPuebla = nowUTC.withZoneSameInstant(ZoneId.of("America/Mexico_City"));
        System.out.println("Hora en Puebla, México: " + nowPuebla);
    }
}
