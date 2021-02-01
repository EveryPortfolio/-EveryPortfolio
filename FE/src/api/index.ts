import { Axios } from './config';

export const loginAPI = (params: any): Promise<string> => {
  return Axios('post', '/api/user/login', params);
};

export const createAPI = (params: any): Promise<string> => {
  return Axios('post', '/api/user/create', params);
};

export const checkIDAPI = (params: any): Promise<string> => {
  return Axios('get', `/api/user/check-id/?id=${params.id}`, null);
};
