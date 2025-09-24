package core.domain;

import jdk.jshell.spi.ExecutionControl;

public record Endereco(
        int CEP, String rua, int numero, String complemento)
{ }

