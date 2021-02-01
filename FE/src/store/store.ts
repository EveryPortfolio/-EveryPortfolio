import { applyMiddleware, createStore, $CombinedState } from 'redux';
import promiseMiddlerware from 'redux-promise-middleware';
import reduxThunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import { persistStore } from 'redux-persist';
import { createWrapper } from 'next-redux-wrapper';
import reducer from './modules';

const middlewrares = [logger, promiseMiddlerware, reduxThunk];

const makeStore = () => {
  const store = createStore(reducer, undefined, composeWithDevTools(applyMiddleware(...middlewrares)));
  (store as any).__persistor = persistStore(store);
  console.log('persist:', (store as any).__persistor);
  return store;
};

export const wrapper = createWrapper(makeStore);
