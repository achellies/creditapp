import App from '../app';
import housingFundRouter from '../routes/housingFund';
// import appRun from '../common/apprun';

// App.run(appRun);
App.config(['$routeProvider', housingFundRouter]);
