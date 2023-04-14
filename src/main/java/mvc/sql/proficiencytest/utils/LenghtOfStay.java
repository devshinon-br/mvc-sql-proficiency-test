package mvc.sql.proficiencytest.utils;

import java.time.Duration;

public class LenghtOfStay {

    public static String getLenghtOfStay(final long segundos) {
        Duration duracao = Duration.ofSeconds(segundos);

        long anos = duracao.toDays() / 365;
        long meses = duracao.toDays() % 365 / 30;
        long dias = duracao.toDays() % 365 % 30;
        long horas = duracao.toHours() % 24;
        long minutos = duracao.toMinutes() % 60;
        long segs = duracao.getSeconds() % 60;

        StringBuilder sb = new StringBuilder();

        if (anos > 0) {
            sb.append(anos).append(" ano(s) ");
        }
        if (meses > 0) {
            sb.append(meses).append(" mês(es) ");
        }
        if (dias > 0) {
            sb.append(dias).append(" dia(s) ");
        }
        if (horas > 0) {
            sb.append(horas).append(" hora(s) ");
        }
        if (minutos > 0) {
            sb.append(minutos).append(" minuto(s) ");
        }
        if (segs > 0) {
            sb.append(segs).append(" segundo(s)");
        }

        return sb.toString();
    }
}
