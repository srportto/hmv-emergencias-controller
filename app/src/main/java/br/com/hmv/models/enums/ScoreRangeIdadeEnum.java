package br.com.hmv.models.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;

public enum ScoreRangeIdadeEnum {
    ATE_1_ANO(1, 200),
    DE_1_ANO_A_10_ANOS(2, 100),
    DE_11_ANOS_A_22_ANOS(3, 50),
    DE_23_ANOS_A_28_ANOS(4, 75),
    DE_29_ANOS_A_45_ANOS(5, 100),
    DE_46_ANOS_A_55_ANOS(6, 150),
    DE_56_ANOS_A_62_ANOS(7, 200),
    MAIS_DE_62_ANOS(8, 300);


    private Integer codigoRangeIdade;
    private Integer score;

    ScoreRangeIdadeEnum(Integer codigoRangeIdade, Integer score) {
        this.codigoRangeIdade = codigoRangeIdade;
        this.score = score;
    }

    public Integer getCodigoRangeIdade() {
        return this.codigoRangeIdade;
    }

    public Integer getScoreRangeIdade() {
        return this.score;
    }

    public static ScoreRangeIdadeEnum obterRangeDeIdade(Integer codigoRangeIdade) {
        for (ScoreRangeIdadeEnum itemScoreRangeIdadeEnum : ScoreRangeIdadeEnum.values()) {
            if (itemScoreRangeIdadeEnum.getCodigoRangeIdade() == codigoRangeIdade) {
                return itemScoreRangeIdadeEnum;
            }
        }
        throw new IllegalArgumentException(String.format("Codigo de range de idades %i não conhecido ", codigoRangeIdade));
    }

    public static ScoreRangeIdadeEnum obterRangeDeIdade(LocalDate dataNascimentoPaciente) {
        Logger logger = LoggerFactory.getLogger(ScoreRangeIdadeEnum.class);
        String logCode = "obterRangeDeIdade(LocalDate)";
        logger.info("{} - data nascimento passada {}", logCode, dataNascimentoPaciente);

        var dataCorrente = LocalDate.now();
        var periodoDeTempoPassadoDoNascimentoAteADataCorrente = Period.between(dataNascimentoPaciente, dataCorrente);
        var qtdeAnosIdadePaciente = periodoDeTempoPassadoDoNascimentoAteADataCorrente.getYears();
        var scoreRangeIdadeEnum = ScoreRangeIdadeEnum.MAIS_DE_62_ANOS;

        if (qtdeAnosIdadePaciente <= 1) scoreRangeIdadeEnum = ScoreRangeIdadeEnum.ATE_1_ANO;

        if (qtdeAnosIdadePaciente > 1 && qtdeAnosIdadePaciente <= 10)
            scoreRangeIdadeEnum = ScoreRangeIdadeEnum.DE_1_ANO_A_10_ANOS;

        if (qtdeAnosIdadePaciente > 10 && qtdeAnosIdadePaciente <= 22)
            scoreRangeIdadeEnum = ScoreRangeIdadeEnum.DE_11_ANOS_A_22_ANOS;

        if (qtdeAnosIdadePaciente > 22 && qtdeAnosIdadePaciente <= 28)
            scoreRangeIdadeEnum = ScoreRangeIdadeEnum.DE_23_ANOS_A_28_ANOS;

        if (qtdeAnosIdadePaciente > 28 && qtdeAnosIdadePaciente <= 45)
            scoreRangeIdadeEnum = ScoreRangeIdadeEnum.DE_29_ANOS_A_45_ANOS;

        if (qtdeAnosIdadePaciente > 45 && qtdeAnosIdadePaciente <= 55)
            scoreRangeIdadeEnum = ScoreRangeIdadeEnum.DE_46_ANOS_A_55_ANOS;

        if (qtdeAnosIdadePaciente > 55 && qtdeAnosIdadePaciente <= 62)
            scoreRangeIdadeEnum = ScoreRangeIdadeEnum.DE_56_ANOS_A_62_ANOS;

        if (qtdeAnosIdadePaciente > 62) scoreRangeIdadeEnum = ScoreRangeIdadeEnum.MAIS_DE_62_ANOS;

        logger.info("{} - score do range de idade obtido com sucesso {}", logCode, scoreRangeIdadeEnum.name());
        return scoreRangeIdadeEnum;
    }
}
