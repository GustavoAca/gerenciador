#!/bin/bash

# Obtém a versão atual do arquivo pom.xml abaixo da linha <artifactId>gerenciador</artifactId>
versao_atual=$(awk -F '[<>]' '/<artifactId>gerenciador<\/artifactId>/{getline; print $3}' pom.xml)

# Divide a versão em partes (por exemplo, 1.0.0 será dividido em 1, 0 e 0)
IFS='.' read -r -a partes <<< "$versao_atual"

# Incrementa o segundo número na versão
novo_numero=$(( ${partes[1]} + 1 ))

# Atualiza a versão no arquivo pom.xml com o novo número
nova_versao="${partes[0]}.${novo_numero}.${partes[2]}"
sed -i "/<artifactId>gerenciador<\/artifactId>/!b;n;c<version>${nova_versao}</version>" pom.xml

echo "Versão anterior: $versao_atual"
echo "Nova versão: $nova_versao"