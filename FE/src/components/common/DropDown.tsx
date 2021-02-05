import React, { useState } from 'react';
import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import { NavLink } from './NavLink';
import { requestLogout } from '../../store/modules/user';

const Wrapper = styled.div`
  margin: auto;
  position: relative;
  font-size: 24px;
`;

const ViewName = styled.div`
  position: relative;
  border-radius: 10px;
  background-color: black;
  color: white;
  border: 1px solid black;
  cursor: pointer;
`;

const DropList = styled.div<{ flag: boolean }>`
  display: ${(props) => (props.flag ? 'block' : 'none')};
  position: absolute;
  left: 0;
  top: 115%;
  z-index: 5;
  width: 373%;
  height: 900%;
  font-size: 25px;
  border: 1px solid lightgray;
  cursor: pointer;
`;

const Logout = styled.div`
  margin-top: 10%;
  height: 19%;
`;

export const DropDown = ({ name }: { name: string }): JSX.Element => {
  const [flag, setFlag] = useState(false);
  const dispatch = useDispatch();

  const onClickLogout = () => {
    dispatch(requestLogout());
    window.location.reload();
  };
  return (
    <Wrapper>
      <ViewName onClick={() => setFlag(!flag)}>{name}</ViewName>
      <DropList flag={flag}>
        <NavLink path='info' name='내정보' />
        <NavLink path='myportfolio' name='내 포트폴리오' />
        <NavLink path='myproject' name='내 프로젝트' />
        <NavLink path='like' name='좋아요 누른 포트폴리오' />
        <Logout onClick={() => onClickLogout()}>로그아웃</Logout>
      </DropList>
    </Wrapper>
  );
};
