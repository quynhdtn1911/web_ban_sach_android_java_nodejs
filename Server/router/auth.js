const express = require('express')
const router = express.Router()
const authController = require('../controller/AuthController')

router.post('/login', authController.login)
router.post('/regis', authController.regis)

module.exports = router