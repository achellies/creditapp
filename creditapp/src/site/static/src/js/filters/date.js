export function done(){
    return function(val){
        return val + ':' + (new Date()).toLocaleDateString();
    }
}