import { loginAPI } from '../../api/index';
import { createPromiseThunk, reducerUtils, handleAsyncActions } from '../../lib/asyncUtils';

const LOGIN_PENDING = 'user/LOGIN_PENDING';
const LOGIN_FULFILLED = 'user/LOGIN_FULFILLED';
const LOGIN_REJECTED = 'user/LOGIN_REJECTED';

const LOGOUT = 'user/LOGOUT';

export const requestLogin = createPromiseThunk('user/LOGIN', loginAPI);
export const requestLogout = () => ({ type: LOGOUT });

const initialState = {
  user: reducerUtils.initial(),
};

export default function posts(state = initialState, action: any): any {
  switch (action.type) {
    case LOGIN_PENDING:
    case LOGIN_FULFILLED:
    case LOGIN_REJECTED:
      return handleAsyncActions('user/LOGIN', 'user')(state, action);
    case LOGOUT:
      return {
        ...state,
        user: reducerUtils.initial(),
      };
    default:
      return state;
  }
}
