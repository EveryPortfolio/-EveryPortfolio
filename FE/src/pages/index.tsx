import * as React from 'react';
import { Provider } from 'react-redux';
import LoginComponent from '../components/LoginComponent/LoginComponent';
import { GlobalStyle } from '../styles/GlobalStyle';
import store from '../store/store';

const Index: React.FunctionComponent = () => {
  return (
    <div>
      <Provider store={store}>
        <GlobalStyle />
        <LoginComponent />
      </Provider>
    </div>
  );
};

export default Index;
