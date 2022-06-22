const User = require('../model/User')
class AuthController{
    async login(req, res){
        const body = req.body, email = body.email, password = body.password
        const result = await User.find({email: email, password: password})
        if(result.length == 0){
            res.status(200).json({})
        }else{
            const rsUser = result[0]
            res.status(200).json({
                name: rsUser.name,
                email: rsUser.email, 
                password: rsUser.password
            })
        }
    }
    async regis(req, res){
        const body = req.body, name = body.name, email = body.email, password = body.password
        const result = await User.find({email: email})
        if(result.length != 0){
            res.status(200).json({
            status: false
            })
        }else{
            await User.create({
                name,
                email, 
                password
            })
            res.status(200).json({
                status: true
            })
        }
    }
}

module.exports = new AuthController