package driver.exceptions

class Server502ResponseException extends Exception("Server has returned a 502 Bad Gateway Response")

class Server500ResponseException(stackTraceMessage: String) extends Exception(stackTraceMessage)

class TransactionNotFoundException(authorityId: String) extends Exception(s"Unable to find a transaction for authorityId: $authorityId")

