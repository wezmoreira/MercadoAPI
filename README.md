# Mercado API

## Endpoints

## CLIENTE
### Post 
- http://localhost:8082/api/cliente
#### O Cpf deve ser válido!
```
{
    "cpf":"12345678900",
    "nome": "Fulano"
}
```
### Get
- http://localhost:8082/api/cliente

#### get por cpf 
- http://localhost:8082/api/cliente/{cpf}

### Update
- http://localhost:8082/api/cliente/{cpf}

## CLIENTES_CARTOES

### Post
- http://localhost:8082/api/cliente/{cpf}/cartoes/
```
{
    "nome_cartao": "Nome cartao",
    "numero_cartao": "1234567",
    "codigo_seguranca": "123",
    "marca": "MASTERCARD",
    "mes_expiracao": "5",
    "ano_expiracao": "23"
}        
```

### Get
- http://localhost:8082/api/cliente/{cpf}/cartoes/

### Update
- http://localhost:8082/api/cliente/{cpf}/cartoes/{id}

## ITEMS

### Post
- http://localhost:8082/api/item/
#### Data_Criação deve ser menor que Data_validade
```
{
    "nome": "Headset Gamer",
    "data_criacao": "02/06/2021 01:01:20",
    "data_validade": "02/06/2025 01:01:20",
    "valor": 200.0,
    "descricao": "Fone gamer",
    "estoque": 50
}       
```
### Get
- http://localhost:8082/api/item/

### Get por id
- http://localhost:8082/api/item/{id}

### Update
- http://localhost:8082/api/item/{id}


## CHECKOUT 

### Post
- http://localhost:8082/api/checkout
```
{
    "itens" : [
        {
            "skuId" : "Aqui deve ser inserido o "skuId" gerado quando cadastra um item",
            "qtd" : 1
        },
        {
            "skuId": "Aqui deve ser inserido o "skuId" gerado quando cadastra um item",
            "qtd": 1
        }
    ],
    "cliente_info" : {
        "clienteId" : "Aqui deve ser inserido o CPF do cliente",
        "cartaoId" : "Aqui deve ser inserido o Id do cartão"
    }
}    
```




# Endpoints 

## Endpoints

### Post
- http://localhost:8080/api/pedido

#### Body do Post (deve ser um Cpf válido!) 
#### E "mes_expiracao" no campo  "pedidoPagamento" deve seguir o padrão de mês (1, 2, 3, 4...) sem a necessidade do "0"
```
{
    "cpf": "12345678900",
    "total": 250.0,
    "status": "EM_ANDAMENTO",
    "status_pagamento": "PROCESSING",
    "tipo_pagamento": "CREDIT_CARD",
    "itens": [
        {
            "nome": "Headset",
            "data_criacao": "21/06/1997 03:25:03",
            "data_validade": "23/06/1998 03:25:03",
            "valor": 260.0,
            "descricao": "Headset gamer",
            "ofertas": [
                {
                    "nome": "wes",
                    "data_criacao": "20/06/2023 02:20:02",
                    "data_validade": "20/06/2024 02:20:02",
                    "desconto": 10.0,
                    "descricao": "Teste"
                }
            ]
        }
    ],
    "pedidoPagamento": 
        {
            "numero_cartao": "123456789",
            "nome_cartao": "Joao Silva",
            "codigo_seguranca": "123",
            "marca": "MASTERCARD",
            "mes_expiracao": "3",
            "ano_expiracao": "25",
            "moeda": "BRL",
            "valor": 250.0
        }  
}
```


### Get
- http://localhost:8080/api/pedido

#### get por cpf 
- http://localhost:8080/api/pedido/?cpf=10302373950

#### get por Sort do valor asc
- http://localhost:8080/api/pedido?sort=total,asc

#### get por Sort do valor desc
- http://localhost:8080/api/pedido?sort=total,desc

### Get por ID
- http://localhost:8080/api/pedido/{id}

### Patch Pedido
- http://localhost:8080/api/pedido/{id}

### Patch Item
- http://localhost:8080/api/item/{id}

### Delete Pedido
- http://localhost:8080/api/pedido/{id}



### Referências que utilizei para executar esse projeto:

https://stackoverflow.com/questions/9841623/mockito-how-to-verify-method-was-called-on-an-object-created-within-a-method

https://www.netjstech.com/2015/06/method-reference-in-java-8.html

https://cursos.alura.com.br/forum/topico-beanvalidator-cpf-53635


https://www.baeldung.com/jpa-size-length-column-differences

https://fabiano-goes.medium.com/api-rest-com-paginação-usando-spring-data-e-query-9eddb29c9223

https://www.youtube.com/watch?v=SzcvuHjRJKE


### Referências que utilizei para executar esse projeto:

https://www.baeldung.com/spring-5-webclient

https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/boot-features-webclient.html

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.Builder.html

https://reflectoring.io/spring-webclient/

https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/crypto.html

https://docs.spring.io/spring-security/reference/features/integrations/cryptography.html#:~:text=The%20Spring%20Security%20Crypto%20module,Security%20(or%20Spring)%20code.

https://www.tabnine.com/code/java/methods/io.jsonwebtoken.JwtBuilder/signWith

https://stackoverflow.com/questions/40252903/static-secret-as-byte-key-or-string
