import { RootStateOrAny } from 'react-redux';
import { combineReducers, $CombinedState } from 'redux';
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import user from './user';

const persistConfig = {
  key: 'nextjs',
  storage,
  whitelist: ['user'],
};

const rootReducer = function () {
  return combineReducers({
    user,
  });
};
export default persistReducer<RootStateOrAny>(persistConfig, rootReducer());
