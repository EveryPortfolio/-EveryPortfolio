import { applyMiddleware, createStore } from 'redux';
import promiseMiddlerware from 'redux-promise-middleware';
import reduxThunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import reducer from './modules';

const createStoreWidthMiddleware = createStore(
  reducer,
  composeWithDevTools(applyMiddleware(promiseMiddlerware, reduxThunk, logger)),
);

export default createStoreWidthMiddleware;
