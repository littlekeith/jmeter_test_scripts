def printErrorMsg(msg) {
    log.info("==================================");
    log.info("Call printErrorMsg");
    prev.setSuccessful(false);
    prev.setResponseCode("500");
    prev.setResponseMessage(msg);
    prev.setResponseData("");
    log.info(msg);
    log.info("==================================");
    return true; 
}

def sum(String[] _parameters) {
    log.info("==================================");
    log.info("Call sum");
    long sum = 0;
    for (i = 1; i < _parameters.length; i++) {
        try {
            sum += _parameters[i].toInteger();
            
        } catch(Exception e) {

            log.error("Exception: ${e}");
            break
        }
        
    }
    log.info("sum = " + sum)
    log.info("==================================");

}

def mul(String[] _parameters) {
    log.info("==================================");
    log.info("Call mul");
    long result = (args.length > 1) ? 1 : 0;
    for (i = 1; i < _parameters.length; i++) {
        
        try {
            result *= _parameters[i].toInteger();
            
        } catch(Exception e) {

            log.error("Exception: ${e}");
            break
        }
    }
    log.info("multiply = " + result)
    log.info("==================================");

}
//check if the Parameters is null
def operation = Parameters ? args[0].toLowerCase() : 'At least one parameter';


//check the operator
if (operation != 'sum' && operation != 'mul') {

    def errmsg = "Missing or incorrect the args operation. Expected 'sum' or 'mul'. But found '" + operation + "'";
    printErrorMsg(errmsg);
    return true;
}
 
log.info("The operations is: " + operation);
 
//check array item must be integer
for (i = 1; i < args.length; i++) {
    def errmsg = "Need integer number in the list: " + args[i];
    // printErrorMsg(errmsg);
    try {
        def index = args[i].toInteger();
        
    } catch(Exception e) {
        log.error("Exception: ${e}");
        printErrorMsg(errmsg);
        return true
    }
}
 
//calculate in case of sum
if (operation == 'sum') {
    sum(args);
    return true;
}
 
//calculate in case of multiply
if (operation == 'mul') {
    mul(args);
    return true;
}