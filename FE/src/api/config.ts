import axios, { AxiosRequestConfig } from 'axios';
import { urlObjectKeys } from 'next/dist/next-server/lib/utils';

const DOMAIN = '';
const Config = axios.create();

Config.interceptors.request.use((value: AxiosRequestConfig) => {
  const config = value;
  config.params = {
    ...config.params,
  };
  return config;
});

Config.interceptors.response.use(
  (response) => response,
  (error) => {
    return Promise.reject(error);
  },
);

export const Axios = (method: any, url: string, data: any) => {
  return Config({
    method,
    url: DOMAIN + urlObjectKeys,
    data,
  })
    .then((res) => res.data)
    .catch((err) => console.log(err));
};
