<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@include file="../main/header.jsp"%>--%>
<!DOCTYPE html>
<html lang="ko" dir="ltr" class='com_virtuemart view-productdetails itemid-167 mm-hover'>
<head>
  <title>개발사 후원페이지</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainunigames.css">
  <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script> <!-- 마이크로소프트 jQuery-->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
  <meta name="apple-mobile-web-app-capable" content="YES"/>

</head>

<body>

<jsp:include page="../main/header.jsp"></jsp:include>
<div id="image-top-bg" ></div>
<div class="parent-container" style="display: grid">
    <div class="main-container">
      <div class="row equal-height">
        <!-- MAIN CONTENT -->
        <div id="t3-content" class="t3-content col-md-12">
          <div id="system-message-container"></div>
          <div class="productdetails-view productdetails">
            <div class="page-header">
              <div style="clear:both;position:absolute;margin-top:18px;z-index:10;text-align:center;">
                <div style="display:block;border: 1px solid rgba(255,255,255,0.1);padding-left:10px;padding-right:10px;"><img src="${pageContext.request.contextPath}/images/img-game-icon_1.jpg" width="80"/></div>
              </div>
              <h1 style="font-size:20px;padding-left:110px;display:inline-block;text-transform:initial" >

							<span>
																												[특전제공] 스트리트 파이터 6 디럭스 에디션							</span><br/>
                <span style="text-transform:none" id="game_name">${game.name}</span>
              </h1>
            </div>

            <div class="vm-product-container">
              <div class="left">
                <div class="vm-product-media-container" itemprop='video' itemscope itemtype='https://schema.org/VideoObject'>
                  <div class="main-image">
                    <div id="screenshot_image"><div class='embed-responsive embed-responsive-16by9'><iframe class='embed-responsive-item' src='https://www.youtube.com/embed/BjxYggXttHU'></iframe></div></div>
                    <div class="clear"></div>
                  </div>
                  <div class="gallery-wrapper">
                    <div class="gallery detail-gallery">
                      <div class="item screenshot_thumbnail" style='cursor:pointer'>
                        <div class='media_player_marker'><span>▶</span></div>												<img src="https://img.youtube.com/vi/BjxYggXttHU/1.jpg" data-type="media" data-url="<div class='embed-responsive embed-responsive-16by9'><iframe class='embed-responsive-item' src='https://www.youtube.com/embed/BjxYggXttHU'></iframe></div>" itemprop="thumbnailUrl">
                      </div>
                      <div class="item screenshot_thumbnail" style='cursor:pointer'>
                        <img src="${pageContext.request.contextPath}/images/img-game-thumnail_1.jpg" data-type="image/jpeg" data-url="<img src='${pageContext.request.contextPath}/images/img-game-thumnail_1.jpg'>" itemprop="thumbnailUrl">
                      </div>
                      <div class="item screenshot_thumbnail" style='cursor:pointer'>
                        <img src="${pageContext.request.contextPath}/images/img-game-thumnail_2.jpg" data-type="image/jpeg" data-url="<img src='${pageContext.request.contextPath}/images/img-game-thumnail_2.jpg'>" itemprop="thumnailUrl">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="clear"></div>




                <div class="vm-productdetail-tabs-container">
                  <ul class="nav nav-tabs" style="margin-bottom:8px">
                    <li class="active"><a href="#goods_info3" data-toggle="tab">게임정보</a></li>
                    <li><a href="#goods_info5" data-toggle="tab">시스템 요구사항</a></li>
                  </ul>
                  <div id="myTabContent" class="tab-content">
                    <div id="goods_info2" class="tab-pane fade">
                      <div class="product-description">
                        <p><br></p>												</div>
                    </div>
                    <div id="goods_info3" class="tab-pane fade active in">
                      <div class="product-description">
                        <div id="package_info">
                          <h5><span class="fa fa-square"></span> &nbsp;[특전제공] 스트리트 파이터 6 디럭스 에디션 구성정보</h5>
                        </div>
                      </div>
                      <div class="product-description">
                        <p><b style="color: rgb(255, 170, 0);">※ 예약 특전은 한정 수량으로 조기 품절될 수 있습니다.<br>※&nbsp;</b><span style="color: rgb(255, 170, 0);"><b>제품 코드(키)는 발송은 추가 공지를 통해 안내해 드릴 예정입니다.</b></span><br><br><b style="color: rgb(255, 170, 0);">&nbsp;</b><span style="color: rgb(40, 180, 243);"><b>■ 예약 구매 특전&nbsp;<br></b></span><img src="https://cdn.akamai.steamstatic.com/steam/apps/1364780/extras/13_Custom-Images_PreorderBonus.png?t=1678858664"><br><b>Outfit 1 색상 10(춘리, 제이미, 마농, 디제이, 주리, 켄)<br>&nbsp;</b></p><hr><p><br><b style="color: rgb(255, 170, 0);">&nbsp;</b><span style="color: rgb(40, 180, 243);"><b>■&nbsp;</b></span><span style="color: rgb(40, 180, 243);"><b>스트리트 파이터 6 디럭스 에디션<br></b></span><img src="https://cdn.akamai.steamstatic.com/steam/apps/1364780/extras/13_Custom-Images_DX.png?t=1678858664"><br><b>스트리트 파이터 6 디럭스 에디션</b><br>• 기본 게임<br>• Year 1 캐릭터 패스<br>　┗추가 캐릭터 4명<br>　┗추가 캐릭터 4명의 색상: Outfit 1 색상 3~10<br>　┗구입 특전: 드라이브 티켓 4,200장<br><br><b><span style="color: rgb(255, 170, 0);">※추가 콘텐츠는 발매일 이후에 순차적으로 배포됩니다.</span><br><span style="color: rgb(255, 170, 0);">※상품의 자세한 정보, 발매 스케줄은 <a href="https://www.streetfighter.com/6/ko-kr/" target="_blank">공식 사이트</a>에서 확인해 주시기 바랍니다.</span></b><br>&nbsp;</p><hr><p><br>시리즈 최신작 『Street Fighter 6(스트리트 파이터 6)』, 2023년 6월 2일 발매 결정!<br><br>RE ENGINE으로 개발된 본 작품은 지금까지의 시리즈에는 없었던 대형 모드: World Tour, Fighting Ground, Battle Hub로 구성되어 있다.<br><br>■개성 넘치는 18명의 파이터<br>류, 춘리, 루크, 제이미, 킴벌리 등, 레전드부터 뉴 제너레이션까지의 파이터가 인상적인 비주얼과 다채로운 기술을 앞세워 지금 이곳에 모였다.<br><img src="https://cdn.akamai.steamstatic.com/steam/apps/1364780/extras/SF6_18Characters_Steam.png?t=1678858664"><br><br>■전투의 무대를 지배하라<br>「Fighting Ground(파이팅 그라운드)」에서는 시리즈를 계승한 격투 모드는 물론, 커맨드 입력의 벽을 허무는 「모던」 타입 조작, 상황에 따라 AI가 서포트해 주는 「다이내믹」 타입 조작 등을 제공. 게임 대회의 현장감을 집에서 맛볼 수 있는 배틀 자동 중계방송 기능도 탑재.<br>공수 일체의 핵심이 되는 「드라이브 시스템」. 효과적으로 기술을 발동하여 화려한 연출과 통쾌한 배틀을 체감하라.<br><img src="https://cdn.akamai.steamstatic.com/steam/apps/1364780/extras/SF6_FightingGround_Steam.png?t=1678858664"><br><br>■강함을 추구하는 모험을 떠나자<br>싱글 플레이어의 몰입형 스토리 모드 「World Tour(월드 투어)」에서는 플레이어의 분신이 되는 주인공을 풍부한 아바타 크리에이션 기능으로 만들어 스트리트 파이터의 세계 주민들과 만나면서 류, 춘리와 같은 스승의 제자가 되어 기술을 배우고 다양한 적과 싸우며 「강함이란 무엇인가?」의 해답을 찾아 여행한다.<br><img src="https://cdn.akamai.steamstatic.com/steam/apps/1364780/extras/SF6_WorldTour_Steam.png?t=1678858664"><br><br>■세계의 파이터와 주먹을 맞대자<br>월드 투어에서 단련한 아바타가 모여 전 세계의 플레이어와 소통을 즐길 수 있는 「Battle Hub(배틀 허브)」. 대전 오락기에 앉으면 바로 뜨거운 전투를 즐길 수 있는 「배틀 허브 매치」와 「익스트림 배틀」 외에도 「게임 센터」의 오락기에서 캡콤의 명작 아케이드 게임을 플레이할 수도 있다!<br><img src="https://cdn.akamai.steamstatic.com/steam/apps/1364780/extras/SF6_BattleHub_Steam.png?t=1678858664"><br><br>월드 워리어를 향한 길은 이곳에서 시작된다.<br><br><b>【온라인 플레이에 관하여】</b><br>캡콤은 이 게임에 관해, 온라인 전용 콘텐츠를 포함한 각종 온라인 서비스를 제공합니다.<br>※인터넷 접속 환경이 없는 경우 일부 이용이 불가능한 요소가 있으므로 주의해 주시기 바랍니다.<br>※온라인 전용 콘텐츠를 이용하려면 CAPCOM ID가 필요합니다.<br>※CAPCOM ID의 서비스 내용, 이용 방법 등은 CAPCOM ID의 웹사이트 <a href="https://cid.capcom.com/" target="_blank">https://cid.capcom.com/</a> 를 참고해 주시기 바랍니다. 또한, CAPCOM ID 이용에 있어 연령 제한이 설정되어 있는 경우가 있습니다.<br>※피치 못할 사정이 있는 경우, 캡콤으로 인해 온라인 서비스가 일시 정지 또는 중단되는 경우가 있습니다.</p>												</div>
                    </div>
                    <div id="goods_info4" class="tab-pane fade">
                      <div class="product-description">
                        <p><br></p>												</div>
                    </div>
                    <div id="goods_info5" class="tab-pane fade">
                      <div class="product-description">
                        <p><b>최소:</b><br>64비트 프로세서와 운영 체제가 필요합니다<br>운영체제: Windows 10 (64 bit required)<br>프로세서: Intel Core i5-7500 / AMD Ryzen 3 1200<br>메모리: 8 GB RAM<br>그래픽: GTX1060 (VRAM 6GB) / Radeon RX 580 (VRAM 4GB)<br>DirectX: 버전 12<br>저장공간: 60 GB 사용 가능 공간<br>추가 사항: - The framerate may drop during graphic-intensive scenes. - As this is a title still in development, the system requirements are still subject to change.<br><br><b>권장:</b><br>64비트 프로세서와 운영 체제가 필요합니다<br>운영체제: Windows 10 (64 bit required)<br>프로세서: Intel Core i7 8700 / AMD Ryzen 5 3600<br>메모리: 16 GB RAM<br>그래픽: RTX2070 / Radeon RX 5700XT<br>DirectX: 버전 12<br>저장공간: 60 GB 사용 가능 공간<br>추가 사항: - The framerate may drop during graphic-intensive scenes. - As this is a title still in development, the system requirements are still subject to change.<br>&nbsp;</p><hr><br>©CAPCOM CO., LTD. ALL RIGHTS RESERVED.<br>													<br><p>※ 시스템 요구 사항은 출시 버전을 기준으로 하며, 컨텐츠 추가와 업데이트로 인한 변경 사항이 있을 수 있습니다.</p>
                      </div>
                    </div>
                    <div id="goods_info8" class="tab-pane fade">
                      <div class="product-description">
                        <p><br></p>												</div>
                    </div>
                    <div id="goods_event" class="tab-pane fade">
                      <div class="product-description">
                        <p><br></p>												</div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="right">
                <div class="vm-product-details-container" >
                  <div class="module normal-module" >
                    <div id="goods_img" ><img src="${pageContext.request.contextPath}/images/img-game-thumnail_1.jpg" width="320" height="150"  title="유니크 게임즈" onerror="this.src='/images/404.jpg'"></div>
                    <div class="module-inner">
                      <div class="module-ct">
                        <div class="vmgroup single-product  normal-module" >
                          <div class="vmproduct normal-module" >
                            <div class="spacer">

                              <div class="product-short-description">

                              </div>

                              <div id="product-price2" style="padding-top:10px">
                                <div class="PricebasePrice vm-display vm-price-value">
                                  <span class="vm-price-desc" >제작 </span>
                                  <span class="PricebasePrice" ><a href="../detail/getIntro?id=2" style="font: italic normal 900 18px Apex New; letter-spacing: 0px;color: #1c2025">에픽콘솔게임 Team</a>
                                  </span>
                                  <span id="company_id" style="display: none">${companyVo.company_id}</span>
                                </div>
                                <span class="price-crossed" ></span>
                              </div>

                              <div class="product-info">
                                <div class="vm-display">
                                  <span class="vm-desc">제작기간 </span>
                                  <span class="vm-value">24개월 15일</span>
                                </div>
                                <div class="vm-display">
                                  <span class="vm-desc">지원모드 </span>
                                  <span class="vm-value">멀티 플레이</span>
                                </div>
                                <div class="vm-display release_date">
                                  <span class="vm-desc">출시일 </span>
                                  <span class="vm-value">
																																				2023-06-12																																				</span>
                                </div>
                                <div class="vm-display">
                                  <span class="vm-desc">장르 </span>
                                  <span class="vm-value">격투</span>
                                </div>
                                <div class="vm-display">
                                  <span class="vm-desc">퍼블리셔 </span>
                                  <span class="vm-value" >HandyGames</span>
                                </div>
                                <div class="vm-display">
                                  <span class="vm-desc">플랫폼 </span>
                                  <span class="vm-value">Steam</span>
                                </div>
                                <div class="vm-display">
                                  <span class="vm-desc">언어 </span>
                                  <span class="vm-value">한국어</span>
                                </div>
                                <div class="vm-display">
                                  <span class="vm-desc">게임패드 </span>
                                  <span class="vm-value">지원함</span>
                                </div>

                              </div>

                              <div class="vmproduct-link" style="text-align:center">
                                <div class="donation-amount">
                                  <span class="label">목표금액</span>
                                  <span class="amount">200,000,000원</span>
                                </div>
                                <div class="donation-amount" style="margin-bottom: 30px">
<%--                                  <span class="label">후원금액</span>--%>
<%--                                  <span class="don-amount">2,000,000원</span>--%>
                                </div>
                                <button type="button" class="btn btn-default btn-lg" id="btn_wishlist" data="50004122" style="width: 90px;">
                                  <img src="${pageContext.request.contextPath}/images/btn_like_select.png">
                                  Like</button>
                                <button type="button" class="btn btn-warning btn-lg" id="btn_donate" style="width: 150px">후원하기</button>

                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>


                <div class="clear"></div>

                <div class="product-share-container">
                  <div class="docs-section">
                    <div class="page-header">
                      <h2 style="font-size:16px;text-indent:130px">공유하기</h2>
                    </div>
                    <div class="row">
                      <div class="input-inline" style="align-content: center ">
                        <button type="button" class="btn" id="btn_facebook" style="background:#4e69a2;color:#ffffff;font-size:13px"><i class="fa fa-facebook" style="background:#4e69a2;color:#ffffff"></i> 페이스북</button>
                        <button type="button" class="btn" id="btn_twitter" style="background:#319de1;color:#ffffff;font-size:13px"><i class="fa fa-twitter" style="background:#319de1;color:#ffffff"></i> 트위터</button>
                        <button type="button" class="btn" id="btn_google" style="background:#df513f;color:#ffffff;font-size:13px"><i class="fa fa-google-plus" style="background:#df513f;color:#ffffff"></i> 구글+</button>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/detail.js"></script>