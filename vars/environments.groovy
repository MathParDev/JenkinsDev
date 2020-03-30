def getList(){
    return ['mathpar.ukma.edu.ua'];
}

def getShortName(String fullName){
    return [
            'mathpar.ukma.edu.ua': 'ukma'
    ].get(fullName)
}

def getPropertiesFilename(String environment){
    return [
            'mathpar.ukma.edu.ua': 'mathpar-ukma.json'
    ].get(environment)
}

def getHostString(String environment){
    return [
            'mathpar.ukma.edu.ua': 'mathpar@mathpar.ukma.edu.ua'
    ].get(environment)
}

