/**
 * export businessComponentsService module
 */

function businessComponentsService(userApiService, $q) {
    var deferred = $q.defer();
    var processRoute = [],
        routeprefix = '/product/:serialno/';
    processRoute['router'] = ['router'];
    processRoute['idcard'] = ['idcard'];
    processRoute['idcardscan'] = ['idcardscan'];
    processRoute['face'] = ['faceauth'];
    processRoute['contactInfo'] = ['contactinfo'];
    processRoute['bankcard'] = ['bankcardselect'];
    processRoute['confirm'] = ['confirm'];
    var resolvePath = function(serialNo, step) {
        return routeprefix.replace(':serialno', serialNo) + processRoute[step];
    }
    return {
        getCreditProcess: function(serialNo) {
            var process = {
                currentStep: "",
                currentPath: ""
            }
            userApiService.getProcess({ serialNo: serialNo })
                .then(function(response) {
                    process.currentStep = response.result.currentStep;
                    process.currentPath = resolvePath(serialNo, process.currentStep);
                    deferred.resolve(process);
                })
            return deferred.promise;
        },
        resolveCreditProcess: function(serialNo, step) {
            var process = {
                currentStep: "",
                currentPath: ""
            }
            process.currentStep = step;
            process.currentPath = resolvePath(serialNo, process.currentStep);
            return process;
        }
    }
}
export default [{
    name: 'businessComponentsService',
    dependences: ['userApiService', '$q'],
    fn: businessComponentsService
}];
