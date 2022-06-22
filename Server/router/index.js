const authRouter = require('./auth')

const router = (app) => {
    app.use('/auth', authRouter)
}
module.exports = router