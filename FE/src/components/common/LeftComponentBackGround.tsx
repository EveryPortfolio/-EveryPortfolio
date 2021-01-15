import React from 'react';
import styled from 'styled-components';

const LeftComponent = styled.div`
  width: 60%;
  background-color: none;
  font-size: 60px;
  padding: 10% 7%;
  padding-bottom: 0px;
  color: white;
`;

export const LeftComponentBackGround = ({ children }: { children: JSX.Element[] }): JSX.Element => {
  return <LeftComponent>{children}</LeftComponent>;
};
