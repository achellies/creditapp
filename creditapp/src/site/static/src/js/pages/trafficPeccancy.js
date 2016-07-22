import App from '../app';
import trafficPeccancyRouter from '../routes/trafficPeccancy';
// import appRun from '../common/apprun';

// App.run(appRun);
App.config(['$routeProvider', trafficPeccancyRouter]);
