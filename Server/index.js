const express = require('express')
const app = express()
const router = require('./router/index.js')
const db = require('./db/config')

//middleware
app.use(express.json())
app.use(express.urlencoded())

//init router
router(app)

//connect db
db.connect()



//listen port
app.listen(5000, '192.168.1.193', () => {
    console.log('Server is running at 5000')
})