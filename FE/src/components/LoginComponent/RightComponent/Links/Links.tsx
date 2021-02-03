import React from 'react';
import Link from 'next/link';
import styled from 'styled-components';

const LinkWrapper = styled.div`
  margin-top: 100px;
  font-size: 24px;
`;

const FlexWrapper = styled.div`
  display: flex;

  div {
    margin-left: 10px;
    margin-top: 0px !important;
  }
`;

const LinkDiv = styled.div`
  margin-top: 20px;
  color: #2a8de8;
  cursor: pointer;
`;

export const Links = (): JSX.Element => {
  return (
    <LinkWrapper>
      <FlexWrapper>
        <span>New to Every Portfolio? </span>
        <Link href='/signup'>
          <LinkDiv>Create an Account!</LinkDiv>
        </Link>
      </FlexWrapper>
      <Link href='/find-id'>
        <LinkDiv>Forgot your ID ?</LinkDiv>
      </Link>
      <Link href='/find-pw'>
        <LinkDiv>Forgot your Password ?</LinkDiv>
      </Link>
    </LinkWrapper>
  );
};
