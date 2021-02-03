import React from 'react';
import { AuthBackground } from '../common';
import { LeftComponent } from './LeftComponent/LeftComponent';
import { RightContainer } from '../../container/SignUpContainer/RightContainer';

const LoginComponent = (): JSX.Element => {
  console.log('Component render');
  return (
    <>
      <AuthBackground>
        <LeftComponent />
        <RightContainer />
      </AuthBackground>
    </>
  );
};

export default LoginComponent;
