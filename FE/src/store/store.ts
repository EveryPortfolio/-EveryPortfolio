import { applyMiddleware, createStore } from 'redux';
import promiseMiddlerware from 'redux-promise-middleware';
import reduxThunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import { persistStore } from 'redux-persist';
import reducer from './modules';

const store = createStore(
  reducer,
  composeWithDevTools(applyMiddleware(promiseMiddlerware, reduxThunk, logger)),
);

const persistor = persistStore(store);

export { store, persistor };
