import React from 'react';
import styled from 'styled-components';
import Link from 'next/link';

const Wrapper = styled.div`
  height: 19%;
`;

interface NavLinkProps {
  path: string;
  name: string;
}

export const NavLink = ({ path, name }: NavLinkProps): JSX.Element => {
  return (
    <Wrapper>
      <Link href={path}>
        <div>{name}</div>
      </Link>
    </Wrapper>
  );
};
