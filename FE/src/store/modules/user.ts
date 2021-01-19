import { loginAPI } from '../../api/index';

const LOGIN_SUCCESS = 'user/LOGINS';

export const requestLogin = (params: any) => {
  /**
   * @TODO
   * API 맞춰서 작성하기
   * const data = loginAPI(params);
   */
  const data = loginAPI(params);
  console.log('result:', data);
  // const data = new Promise((resolve) => {
  //   return resolve('check');
  // });

  return {
    type: LOGIN_SUCCESS,
    payload: data,
  };
};

const initialState = {
  successLogin: '',
};

export default function user(state = initialState, action: any): any {
  switch (action.type) {
    case `${LOGIN_SUCCESS}_FULFILLED`:
      console.log('LOGIN_SUCCESS Type', action);
      return {
        ...state,
        successLogin: action.payload,
      };
    default:
      console.log('Default Type:', action);
      return state;
  }
}
