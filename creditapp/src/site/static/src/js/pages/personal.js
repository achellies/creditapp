import App from '../app';
import personalRouter from '../routes/personal';
// import appRun from '../common/apprun';

// App.run(appRun);
App.config(['$routeProvider', personalRouter]);
