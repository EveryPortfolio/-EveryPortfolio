import * as React from 'react';
import LoginComponent from '../components/LoginComponent/LoginComponent';
import { GlobalStyle } from '../styles/GlobalStyle';

const Index: React.FunctionComponent = () => {
  return (
    <div>
      <GlobalStyle />
      <LoginComponent />
    </div>
  );
};

export default Index;
