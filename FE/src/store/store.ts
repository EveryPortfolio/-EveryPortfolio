import { applyMiddleware, createStore } from 'redux';
import promiseMiddlerware from 'redux-promise-middleware';
import reduxThunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import reducer from './modules';

// const createStoreWidthMiddleware = applyMiddleware(promiseMiddlerware, reduxThunk)(createStore);
const createStoreWidthMiddleware = createStore(
  reducer,
  composeWithDevTools(applyMiddleware(logger, promiseMiddlerware, reduxThunk)),
); // 여러개의 미들웨어를 적용 할 수 있습니다.

export default createStoreWidthMiddleware;
