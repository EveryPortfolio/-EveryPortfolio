import React from 'react';
import styled from 'styled-components';
import Link from 'next/link';
import { useSelector } from 'react-redux';
import { useInput } from '../../hooks/useInput';
import { DropDown } from './DropDown';

const Wrapper = styled.div`
  display: flex;
  height: 5%;
  justify-content: space-evenly;
  align-items: center;
`;

const SearchBar = styled.div`
  display: flex;
  width: 46%;
  height: 75%;
`;

const SearchInput = styled.input`
  width: 90%;
`;

const SearchButton = styled.button`
  width: 10%;
`;

const SearchUser = styled.div`
  display: flex;
  height: 100%;
`;

const Left = styled.div`
  display: block;
`;

const LoginLink = styled.div`
  margin: auto;
  border-radius: 10px;
  background-color: black;
  color: white;
  border: 1px solid black;
  cursor: pointer;
  font-size: 24px;
`;

interface stateProps {
  user: any;
  _persist: any;
}

export const Header = () => {
  const login = useSelector((state: stateProps) => state.user.user);
  const [searchText, , onChangeSearchText] = useInput('');

  return (
    <Wrapper>
      <Left />
      <SearchBar>
        <SearchInput placeholder='' type='text' value={searchText} onChange={onChangeSearchText} />
        <SearchButton type='button' onClick={(e) => console.log('click')}>
          검색
        </SearchButton>
      </SearchBar>
      <SearchUser>
        {login.loginSuccess ? (
          <DropDown name={login.loginSuccess.name} />
        ) : (
          <LoginLink>
            <Link href='/login'>Login</Link>
          </LoginLink>
        )}
      </SearchUser>
    </Wrapper>
  );
};
