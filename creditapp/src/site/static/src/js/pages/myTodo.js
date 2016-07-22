import App from '../app';
import myTodoRouter from '../routes/myTodo';
// import appRun from '../common/apprun';

// App.run(appRun);
App.config(['$routeProvider', myTodoRouter]);
