const amqp = require('amqplib')
const fila = "PRECO"

amqp.connect({
    host: "localhost",
    port: 5672,
    username: 'admin',
    password: 123456
}).then((conexao) => {
    conexao.createChannel()
    .then(canal => {
        canal.consume(fila, (mensagem) => {
            console.log(mensagem.content.toString())
        }, {noAck: true})
    })
    .catch((err) => console.log(err))
})
.catch((err) => console.log(err))
