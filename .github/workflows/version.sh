#!/bin/bash

# Atualiza o número da versão no arquivo pom.xml
nova_versao=$(awk -F '[<>]' '/<version>/{print $3}' pom.xml | awk -F. '{$NF++; OFS="."; print $0}')
sed -i "s|<version>.*</version>|<version>${nova_versao}</version>|" pom.xml

echo "Versão atualizada para: ${nova_versao}"

# Commit e push das mudanças
git add pom.xml
git commit -m "Atualizar versão para ${nova_versao}"
git push origin main