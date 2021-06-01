import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description("Contracts created for get book operations")
    request {
        method GET()
        url("/books")
    }
    response {
        status(200)
    }
}