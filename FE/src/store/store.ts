import { applyMiddleware, createStore } from 'redux';
import promiseMiddlerware from 'redux-promise-middleware';
import reduxThunk from 'redux-thunk';
import reducer from './modules';

const createStoreWidthMiddleware = applyMiddleware(promiseMiddlerware, reduxThunk)(createStore);

export default createStoreWidthMiddleware(reducer);
