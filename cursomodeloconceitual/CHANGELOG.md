# Changelog

## [Unreleased]

### Segurança
- Configuração do Spring Security para uso de JWT, endpoints públicos definidos e CSRF desabilitado apenas para API REST.
- Garantia de que endpoints sensíveis exigem autenticação.
- Upload de arquivos seguro: nomes de arquivos gerados no backend, prevenindo ataques de path traversal.

### Boas Práticas de Código
- Refatoração dos métodos `toString()` das entidades (`Pedido`, `ItemPedido`, `Pessoa`) para uso correto de `StringBuilder`, evitando concatenação de strings dentro do método `append`.
- Validação de extensão de arquivos usando apenas a extensão extraída, sem confiar no nome original do arquivo.

### Testes e Ambiente
- Criação do profile `test` para uso do banco de dados H2 em memória durante testes automatizados.
- Adição de testes de integração para validar segurança no upload de arquivos e funcionamento dos métodos `toString()`.

### Outros
- Melhoria na documentação e comentários explicativos sobre decisões de segurança