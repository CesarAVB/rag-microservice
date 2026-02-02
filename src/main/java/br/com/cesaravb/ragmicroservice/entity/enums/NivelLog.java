package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Níveis de log do sistema
 */
@Getter
@AllArgsConstructor
public enum NivelLog {
    
    TRACE(
        "TRACE",
        0,
        "Logs muito detalhados para debugging profundo"
    ),
    
    DEBUG(
        "DEBUG",
        1,
        "Logs de desenvolvimento e debugging"
    ),
    
    INFO(
        "INFO",
        2,
        "Informações gerais sobre operações"
    ),
    
    WARN(
        "WARN",
        3,
        "Avisos sobre situações anormais"
    ),
    
    ERROR(
        "ERROR",
        4,
        "Erros na aplicação"
    ),
    
    OFF(
        "OFF",
        5,
        "Sem logs"
    );

    private final String nivel;
    private final int ordem;  // 0 = mais verboso, 5 = menos verboso
    private final String descricao;

    /**
     * Obter por nível
     */
    public static NivelLog fromNivel(String nivel) {
        for (NivelLog n : values()) {
            if (n.nivel.equals(nivel)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nível de log inválido: " + nivel);
    }

    /**
     * Verificar se deve logar (baseado em ordem)
     */
    public boolean deveLogar(NivelLog nivelAtual) {
        return this.ordem >= nivelAtual.ordem;
    }
}