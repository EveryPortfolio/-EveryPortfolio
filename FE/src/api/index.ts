import { Axios } from './config';

export const loginAPI = (params: any): Promise<string> => {
  return Axios('post', '/api/user/login', params);
};
