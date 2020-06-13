def getList(){
    return ['mathpar.ukma.edu.ua', 'mathpar.com'];
}

def getShortName(String fullName){
    return [
            'mathpar.ukma.edu.ua': 'ukma',
            'mathpar.com': 'mathpar'
    ].get(fullName)
}

def getPropertiesFilename(String environment){
    return [
            'mathpar.ukma.edu.ua': 'mathpar-ukma.json',
            'mathpar.com': 'mathpar.json'
    ].get(environment)
}

def getHostString(String environment){
    return [
            'mathpar.ukma.edu.ua': 'mathpar@mathpar.ukma.edu.ua',
            'mathpar.com': 'gennadi@mathpar.com'
    ].get(environment)
}

