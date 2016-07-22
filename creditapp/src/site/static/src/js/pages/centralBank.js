import App from '../app';
import centralBankRouter from '../routes/centralBank';
// import appRun from '../common/apprun';

// App.run(appRun);
App.config(['$routeProvider', centralBankRouter]);
