import axios, { AxiosRequestConfig } from 'axios';

const DOMAIN = 'http://localhost:3000';

const Config = axios.create();

Config.interceptors.request.use((value: AxiosRequestConfig) => {
  const config = value;
  config.params = {
    ...config.params,
  };
  return config;
});

Config.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    alert(error.response.data.message);
    return Promise.reject(error);
  },
);

export const Axios = (method: any, url: string, data: any) => {
  return Config({
    method,
    url,
    data,
  }).then((res) => {
    console.log('response:', res);
    return res.data;
  });
};
