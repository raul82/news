package com.example.divtech.models

data class Error (
    val message: String,
    val errors: String,

    val errorCode: String,
    val errorMessage: String,

        )

/*
{
    "message": "The given data\r\nwas invalid.",
    "errors": {
    "depStationCode": [
    "The selected dep\r\nstation code is invalid."
    ],
    "depDate": [
    "The dep date must be\r\na date after or equal to today."
    ]
}
}

{
    "errorCode": "1011",
    "errorMessage": "Место не
    найдено"
}*/
