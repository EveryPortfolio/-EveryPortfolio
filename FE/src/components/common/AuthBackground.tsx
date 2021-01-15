import React from 'react';
import styled from 'styled-components';

const Wrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  color: white;
  z-index: 1;
  position: relative;

  &::after {
    width: 100%;
    min-height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: url('/static/background.jpg');
    color: white;
    z-index: -2;
    position: absolute;
    left: 0;
    top: 0;
    content: '';
    opacity: 0.9;
  }
`;

const ComponentWrapper = styled.div`
  display: flex;
  height: 100vh;
`;

export const AuthBackground = ({ children }: { children: JSX.Element[] }): JSX.Element => {
  return (
    <Wrapper>
      <ComponentWrapper>{children}</ComponentWrapper>
    </Wrapper>
  );
};
