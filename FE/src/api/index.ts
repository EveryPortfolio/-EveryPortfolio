import { Axios } from './config';

export const loginAPI = (params: any): Promise<string> => {
  console.log(params);
  return Axios('post', '/api/user/login', params);
};
