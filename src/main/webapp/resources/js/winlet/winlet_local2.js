(function(jQuery, window, undefined) {
	"use strict";

	var matched, browser;

	jQuery.uaMatch = function(ua) {
		ua = ua.toLowerCase();

		var match = /(chrome)[ \/]([\w.]+)/.exec(ua)
				|| /(webkit)[ \/]([\w.]+)/.exec(ua)
				|| /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua)
				|| /(msie) ([\w.]+)/.exec(ua) || ua.indexOf("compatible") < 0
				&& /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) || [];

		var platform_match = /(ipad)/.exec(ua) || /(iphone)/.exec(ua)
				|| /(android)/.exec(ua) || [];

		return {
			browser : match[1] || "",
			version : match[2] || "0",
			platform : platform_match[0] || ""
		};
	};

	matched = jQuery.uaMatch(window.navigator.userAgent);
	browser = {};

	if (matched.browser) {
		browser[matched.browser] = true;
		browser.version = matched.version;
	}

	if (matched.platform) {
		browser[matched.platform] = true
	}

	// Chrome is Webkit, but Webkit is also Safari.
	if (browser.chrome) {
		browser.webkit = true;
	} else if (browser.webkit) {
		browser.safari = true;
	}

	jQuery.browser = browser;

})(jQuery, window);

/*
 * jQuery hashchange event - v1.3 - 7/21/2010
 * http://benalman.com/projects/jquery-hashchange-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman Dual licensed under the MIT and GPL
 * licenses. http://benalman.com/about/license/
 */
(function($, e, b) {
	var c = "hashchange", h = document, f, g = $.event.special, i = h.documentMode, d = "on"
			+ c in e
			&& (i === b || i > 7);
	function a(j) {
		j = j || location.href;
		return "#" + j.replace(/^[^#]*#?(.*)$/, "$1")
	}
	$.fn[c] = function(j) {
		return j ? this.bind(c, j) : this.trigger(c)
	};
	$.fn[c].delay = 50;
	g[c] = $.extend(g[c], {
		setup : function() {
			if (d) {
				return false
			}
			$(f.start)
		},
		teardown : function() {
			if (d) {
				return false
			}
			$(f.stop)
		}
	});
	f = (function() {
		var j = {}, p, m = a(), k = function(q) {
			return q
		}, l = k, o = k;
		j.start = function() {
			p || n()
		};
		j.stop = function() {
			p && clearTimeout(p);
			p = b
		};
		function n() {
			var r = a(), q = o(m);
			if (r !== m) {
				l(m = r, q);
				$(e).trigger(c)
			} else {
				if (q !== m) {
					location.href = location.href.replace(/#.*/, "") + q
				}
			}
			p = setTimeout(n, $.fn[c].delay)
		}
		$.browser.msie
				&& !d
				&& (function() {
					var q, r;
					j.start = function() {
						if (!q) {
							r = $.fn[c].src;
							r = r && r + a();
							q = $('<iframe tabindex="-1" title="empty"/>')
									.hide().one("load", function() {
										r || l(a());
										n()
									}).attr("src", r || "javascript:0")
									.insertAfter("body")[0].contentWindow;
							h.onpropertychange = function() {
								try {
									if (event.propertyName === "title") {
										q.document.title = h.title
									}
								} catch (s) {
								}
							}
						}
					};
					j.stop = k;
					o = function() {
						return a(q.location.href)
					};
					l = function(v, s) {
						var u = q.document, t = $.fn[c].domain;
						if (v !== s) {
							u.title = h.title;
							u.open();
							t
									&& u.write('<script>document.domain="' + t
											+ '"<\/script>');
							u.close();
							q.location.hash = v
						}
					}
				})();
		return j
	})()
})(jQuery, this);

/*
 * jQuery BBQ: Back Button & Query Library - v1.3pre - 8/26/2010
 * http://benalman.com/projects/jquery-bbq-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman Dual licensed under the MIT and GPL
 * licenses. http://benalman.com/about/license/
 */

(function($, r) {
	var h, n = Array.prototype.slice, t = decodeURIComponent, a = $.param, j, c, m, y, b = $.bbq = $.bbq
			|| {}, s, x, k, e = $.event.special, d = "hashchange", B = "querystring", F = "fragment", z = "elemUrlAttr", l = "href", w = "src", p = /^.*\?|#.*$/g, u, H, g, i, C, E = {};
	function G(I) {
		return typeof I === "string"
	}
	function D(J) {
		var I = n.call(arguments, 1);
		return function() {
			return J.apply(this, I.concat(n.call(arguments)))
		}
	}
	function o(I) {
		return I.replace(H, "$2")
	}
	function q(I) {
		return I.replace(/(?:^[^?#]*\?([^#]*).*$)?.*/, "$1")
	}
	function f(K, P, I, L, J) {
		var R, O, N, Q, M;
		if (L !== h) {
			N = I.match(K ? H : /^([^#?]*)\??([^#]*)(#?.*)/);
			M = N[3] || "";
			if (J === 2 && G(L)) {
				O = L.replace(K ? u : p, "")
			} else {
				Q = m(N[2]);
				L = G(L) ? m[K ? F : B](L) : L;
				O = J === 2 ? L : J === 1 ? $.extend({}, L, Q) : $.extend({},
						Q, L);
				O = j(O);
				if (K) {
					O = O.replace(g, t)
				}
			}
			R = N[1] + (K ? C : O || !N[1] ? "?" : "") + O + M
		} else {
			R = P(I !== h ? I : location.href)
		}
		return R
	}
	a[B] = D(f, 0, q);
	a[F] = c = D(f, 1, o);
	a.sorted = j = function(J, K) {
		var I = [], L = {};
		$.each(a(J, K).split("&"), function(P, M) {
			var O = M.replace(/(?:%5B|=).*$/, ""), N = L[O];
			if (!N) {
				N = L[O] = [];
				I.push(O)
			}
			N.push(M)
		});
		return $.map(I.sort(), function(M) {
			return L[M]
		}).join("&")
	};
	c.noEscape = function(J) {
		J = J || "";
		var I = $.map(J.split(""), encodeURIComponent);
		g = new RegExp(I.join("|"), "g")
	};
	c.noEscape(",/");
	c.ajaxCrawlable = function(I) {
		if (I !== h) {
			if (I) {
				u = /^.*(?:#!|#)/;
				H = /^([^#]*)(?:#!|#)?(.*)$/;
				C = "#!"
			} else {
				u = /^.*#/;
				H = /^([^#]*)#?(.*)$/;
				C = "#"
			}
			i = !!I
		}
		return i
	};
	c.ajaxCrawlable(0);
	$.deparam = m = function(L, I) {
		var K = {}, J = {
			"true" : !0,
			"false" : !1,
			"null" : null
		};
		$.each(L.replace(/\+/g, " ").split("&"), function(O, T) {
			var N = T.split("="), S = t(N[0]), M, R = K, P = 0, U = S
					.split("]["), Q = U.length - 1;
			if (/\[/.test(U[0]) && /\]$/.test(U[Q])) {
				U[Q] = U[Q].replace(/\]$/, "");
				U = U.shift().split("[").concat(U);
				Q = U.length - 1
			} else {
				Q = 0
			}
			if (N.length === 2) {
				M = t(N[1]);
				if (I) {
					M = M && !isNaN(M) ? +M : M === "undefined" ? h
							: J[M] !== h ? J[M] : M
				}
				if (Q) {
					for (; P <= Q; P++) {
						S = U[P] === "" ? R.length : U[P];
						R = R[S] = P < Q ? R[S]
								|| (U[P + 1] && isNaN(U[P + 1]) ? {} : []) : M
					}
				} else {
					if ($.isArray(K[S])) {
						K[S].push(M)
					} else {
						if (K[S] !== h) {
							K[S] = [ K[S], M ]
						} else {
							K[S] = M
						}
					}
				}
			} else {
				if (S) {
					K[S] = I ? h : ""
				}
			}
		});
		return K
	};
	function A(K, I, J) {
		if (I === h || typeof I === "boolean") {
			J = I;
			I = a[K ? F : B]()
		} else {
			I = G(I) ? I.replace(K ? u : p, "") : I
		}
		return m(I, J)
	}
	m[B] = D(A, 0);
	m[F] = y = D(A, 1);
	$[z] || ($[z] = function(I) {
		return $.extend(E, I)
	})({
		a : l,
		base : l,
		iframe : w,
		img : w,
		input : w,
		form : "action",
		link : l,
		script : w
	});
	k = $[z];
	function v(L, J, K, I) {
		if (!G(K) && typeof K !== "object") {
			I = K;
			K = J;
			J = h
		}
		return this.each(function() {
			var O = $(this), M = J || k()[(this.nodeName || "").toLowerCase()]
					|| "", N = M && O.attr(M) || "";
			O.attr(M, a[L](N, K, I))
		})
	}
	$.fn[B] = D(v, B);
	$.fn[F] = D(v, F);
	b.pushState = s = function(L, I) {
		if (G(L) && /^#/.test(L) && I === h) {
			I = 2
		}
		var K = L !== h, J = c(location.href, K ? L : {}, K ? I : 2);
		location.href = J
	};
	b.getState = x = function(I, J) {
		return I === h || typeof I === "boolean" ? y(I) : y(J)[I]
	};
	b.removeState = function(I) {
		var J = {};
		if (I !== h) {
			J = x();
			$.each($.isArray(I) ? I : arguments, function(L, K) {
				delete J[K]
			})
		}
		s(J, 2)
	};
	e[d] = $.extend(e[d], {
		add : function(I) {
			var K;
			function J(M) {
				var L = M[F] = c();
				M.getState = function(N, O) {
					return N === h || typeof N === "boolean" ? m(L, N)
							: m(L, O)[N]
				};
				K.apply(this, arguments)
			}
			if ($.isFunction(I)) {
				K = I;
				return J
			} else {
				K = I.handler;
				I.handler = J
			}
		}
	})
})(jQuery, this);

function ElmRect(elm) {
	if (elm == null)
		return null;

	var posi = elm.offset();
	this.left = posi.left;
	this.top = posi.top;
	this.width = elm[0].offsetWidth;
	this.height = elm[0].offsetHeight;
	this.right = this.left + this.width;
	this.bottom = this.top + this.height;
}

jQuery.fn.winform = function() {
	var settings = $.extend({}, arguments[0]);

	return this
			.each(function() {
				if (this.winsubmit)
					return;

				this.winsubmit = function() {
					if ($(this).attr("enctype") == "multipart/form-data") {
						this.action = this.action + "&"
								+ AeJSEngine.mergeParam(settings.iid, {
									_pg : window.location.pathname,
									_purl : window.location.href
								});
						return true;
					}

					if ($(this).attr('method').toUpperCase() != 'POST') { // get方式无需调用action，直接刷新window
						var params = $.deparam($(this).serialize());
						$(this).find(":checkbox").each(function() {
							if (params[this.name] == undefined)
								params[this.name] = "";
						});
						AeJSEngine.setHash(settings.iid, params);
						AeJSEngine.loadContent(settings.iid);
						AeJSEngine.updateWindows(settings.iid, settings.update);
					} else {
						try {
							if ($(this).attr('hideloading') != 'yes') {
								if (settings.iid == settings.vid)
									AeJSEngine.showLoading('#ap_win_'
											+ settings.iid);
								else
									AeJSEngine.showLoading('#ap_view_'
											+ settings.vid);
							}
						} catch (e) {
						}

						var disabled = new Array();
						var inputs = $(this).find(":input").find(":disabled");
						for ( var i = 0; i < inputs.length; i++)
							if (inputs[i].name != '')
								disabled[disabled.length] = inputs[i].name;

						var fields = new Array();
						inputs = $(this).find(":input");
						for ( var i = 0; i < inputs.length; i++)
							if (inputs[i].name != '')
								fields[fields.length] = inputs[i].name;

						$.ajax({
							type : 'POST',
							url : this.action,
							data : AeJSEngine.mergeParam(settings.iid, $
									.deparam($(this).serialize()), {
								_x : 'y',
								_v : settings.validate,
								_ff : fields,
								_fd : disabled,
								_pg : window.location.pathname,
								_purl : window.location.href
							}),
							success : AeJSEngine.getSubmitFormHandler(
									settings.iid, settings.vid, this),
							error : AeJSEngine.getErrorHandler(settings.iid,
									settings.vid),
							dataType : "text"
						});
					}

					return false;
				};

				this.ajaxValidate = function(input, name, value) {
					if (name == undefined)
						name = input.name;

					AeJSEngine.validating($(input.form).find(
							"span#validate_res_"
									+ name.replace("[", "\\[").replace("]",
											"\\]").replace(".", "\\."))[0]);

					var val = value;

					if (input.type == 'checkbox') {
						if (input.checked)
							val = input.value;
					} else
						val = input.value;

					var param = {
						_vf : name,
						_vv : val,
						_vid : input.id
					};
					param[name] = val;

					$.ajax({
						type : 'POST',
						url : this.action,
						data : AeJSEngine.mergeParam(settings.iid, param, {
							_x : 'y',
							_pg : window.location.pathname,
							_purl : window.location.href
						}),
						success : AeJSEngine.getValidateResponseHandler(
								input.form, name, input),
						dataType : "json"
					});
				};

				$(this).submit(this.winsubmit);

				if (settings.focus)
					$(this).find('input[name="' + settings.focus + '"]')
							.focus();

				this.updateValidate = function() {
					if (settings.validate == 'yes') {
						var form = this;

						$(this)
								.find(":input")
								.each(
										function() {
											if (this.name == null
													|| this.name == '')
												return;

											if ($(this).attr("validate") == "no")
												return;

											if ($(this).prop('tagName') == 'INPUT') {
												var type = $(this).prop("type");
												if (!(type == "text"
														|| type == "checkbox"
														|| type == "radio" || type == "password")) {
													return;
												}
											}

											if (this.m_result != null)
												return;

											this.m_result = $(form)
													.find(
															"span#validate_res_"
																	+ this.name
																			.replace(
																					"[",
																					"\\[")
																			.replace(
																					"]",
																					"\\]")
																			.replace(
																					".",
																					"\\."));
											if (this.m_result.length == 0) {
												this.m_result = $('<span id="validate_res_'
														+ this.name
														+ '"></span>');
												$(this).after(this.m_result);
											}

											if ($(this).attr("tips") != undefined) {
												$(this)
														.focus(
																function() {
																	if (this.m_result
																			.html() == '') {
																		this.m_result
																				.html('<div class="win_tips">'
																						+ $(
																								this)
																								.attr(
																										'tips')
																						+ '</div>');
																	}
																});
												$(this)
														.blur(
																function() {
																	if ($(
																			this.m_result)
																			.find(
																					"div.win_tips").length > 0) {
																		this.m_result
																				.html('');
																	}
																});
											}

											$(this).change(function() {
												this.form.ajaxValidate(this);
											});
										});
					}
				};

				this.updateValidate();
			});
};

var AeJSEngine = {
	ImgBg : new Image(1, 1),
	ImgLoading : new Image(1, 1),
	ImgValidating : new Image(1, 1),

	widCounter : 0,
	isStatic : false,
	isApme : false,
	detectHashChange : true,

	reScriptAll : new RegExp('<script.*?>(?:\n|\r|.)*?<\/script>', 'img'),
	reScriptOne : new RegExp('<script(.*?)>((?:\n|\r|.)*?)<\/script>', 'im'),
	reScriptLanguage : new RegExp('.*?language.*?=.*?"(.*?)"', 'im'),
	reScriptSrc : new RegExp('.*?src.*?=.*?"(.*?)"', 'im'),
	reScriptType : new RegExp('.*?type.*?=.*?"(.*?)"', 'im'),
	reScriptCharset : new RegExp('.*?charset.*?=.*?"(.*?)"', 'im'),
	reCSSAll : new RegExp('<link.*?type="text/css".*?>', 'img'),
	reCSSHref : new RegExp('.*?href="(.*?)"', 'im'),
	reWinPost : new RegExp('win\\$\\.post\\s*\\(', 'img'),
	reWinGet : new RegExp('win\\$\\.get\\s*\\(', 'img'),
	reWinUrl : new RegExp('win\\$\\.url\\s*\\(', 'img'),
	reWinSubmit : new RegExp('win\\$\\.submit\\s*\\(', 'img'),
	reWinlet : new RegExp(
			'^winlet:((\\w+/.+)/(\\w+))(\\?([^\\s]+))?\\s*(\\((.*?)\\))?$'),
	reWinletParam : new RegExp('(\\w+)\\:(\\w+)'),

	hashGroupsByUri : {},
	hashGroupsByIid : {},

	_utf8_decode : function(utftext) {
		if (utftext == null)
			return null;

		utftext = unescape(utftext);

		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while (i < utftext.length) {

			c = utftext.charCodeAt(i);

			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	},

	getWinSettings : function(iid) {
		return $('#ap_win_' + iid)[0].settings;
	},

	getHash : function(iid) {
		var hashgroup = AeJSEngine.hashGroupsByIid[iid].idx;

		try {
			return $.param($.deparam(window.location.hash.slice(1))[hashgroup],
					true);
		} catch (e) {
		}
		return "";
	},

	setHash : function(iid, hash) {
		var hashgroup = AeJSEngine.hashGroupsByIid[iid].idx;
		var params = null;

		try {
			params = $.deparam(window.location.hash.slice(1));
		} catch (e) {
		}

		if (!(params instanceof Object))
			params = {};
		if (params[hashgroup] == undefined)
			params[hashgroup] = {};

		$.extend(params[hashgroup], hash);

		for (property in params[hashgroup]) {
			if (params[hashgroup][property] == '')
				delete params[hashgroup][property];
		}

		AeJSEngine.detectHashChange = false;
		window.location.hash = $.param(params);
	},

	// 合并参数，优先级从高到低为：指定的参数、hash参数、get参数
	mergeParam : function(iid) {
		var obj = {};

		var settings = AeJSEngine.getWinSettings(iid);
		if (settings.params != null)
			$.extend(obj, settings.params);

		var idx = window.location.href.indexOf('?');
		if (idx > 0)
			$.extend(obj, $.deparam(window.location.href.substr(idx + 1)));

		try {
			$.extend(obj, $.deparam(AeJSEngine.getHash(iid)));
		} catch (e) {
		}

		for ( var i = 1; i < arguments.length; i++) {
			if (arguments[i] != null)
				$.extend(obj, arguments[i]);
		}

		return $.param(obj, true);
	},

	ensureVisiable : function(id) {
		if ($(id) == null)
			return;

		try {
			var doc = document.documentElement;
			var rect = new ElmRect($(id));
			var scrollX = 0;
			if (doc.scrollLeft + doc.clientWidth < rect.right)
				scrollX = rect.right - doc.scrollLeft - doc.clientWidth;
			if (doc.scrollLeft + scrollX > rect.left)
				scrollX = rect.left - doc.scrollLeft;

			var scrollY = 0;
			if (doc.scrollTop + doc.clientHeight < rect.bottom)
				scrollY = rect.bottom - doc.scrollTop - doc.clientHeight;
			if (doc.scrollTop + scrollY > rect.top)
				scrollY = rect.top - doc.scrollTop;

			if (scrollX != 0 || scrollY != 0)
				window.scrollBy(scrollX, scrollY);
		} catch (e) {
		}
	},

	clearLoading : function(id) {
		try {
			$(id + "_loading").remove();
		} catch (e) {
		}
	},

	showLoading : function(id, visible) {
		try {
			AeJSEngine.clearLoading(id);

			var rect = new ElmRect($(id));
			try {
				if (AeJSEngine.dlg != null
						&& AeJSEngine.dlg.dialog('isOpen') === true)
					rect = new ElmRect(AeJSEngine.dlg);
			} catch (e) {
			}

			var html;
			if (visible) {
				if (jQuery.browser.version == '6.0')
					html = "<div id='"
							+ id.substr(1)
							+ "_loading' style='z-index:100000;position:absolute;background-color:#999999;filter:alpha(opacity=30);-moz-opacity:0.3;left:"
							+ rect.left
							+ "px;top:"
							+ rect.top
							+ "px;width:"
							+ rect.width
							+ "px;height:"
							+ rect.height
							+ "px'><table width='100%' height='100%' border='0'><tr height='100%'><td align='center' valign='middle'><img src='"
							+ AeJSEngine.ImgLoading.src
							+ "'/></td></tr></table></div>";
				else
					html = "<div id='"
							+ id.substr(1)
							+ "_loading' style='z-index:100000;position:absolute;background:url("
							+ AeJSEngine.ImgBg.src
							+ ");left:"
							+ rect.left
							+ "px;top:"
							+ rect.top
							+ "px;width:"
							+ rect.width
							+ "px;height:"
							+ rect.height
							+ "px'><table width='100%' height='100%' border='0'><tr height='100%'><td align='center' valign='middle'><img src='"
							+ AeJSEngine.ImgLoading.src
							+ "'/></td></tr></table></div>";
			} else {
				html = "<div id='"
						+ id.substr(1)
						+ "_loading' style='z-index:100000;position:absolute;left:"
						+ rect.left + "px;top:" + rect.top + "px;width:"
						+ rect.width + "px;height:" + rect.height
						+ "px'></div>";
			}
			$("body").append(html);
		} catch (e) {
		}
	},

	procStyle : function(cont) {
		var css = cont.match(AeJSEngine.reCSSAll) || [];
		var cssHref = $.map(css, function(tag) {
			return (tag.match(AeJSEngine.reCSSHref) || [ '', '' ])[1];
		});

		var elmHead = document.getElementsByTagName("head")[0];
		var elmLinks = elmHead.getElementsByTagName("link");
		var i;
		var j;
		var newCss;

		for (i = 0; i < cssHref.length; i++) {
			if (cssHref[i] == "")
				continue;

			for (j = 0; j < elmLinks.length; j++) {
				if (elmLinks[j].href == cssHref[i])
					break;
			}

			if (j < elmLinks.length)
				continue;

			newCss = document.createElement('link');
			newCss.type = 'text/css';
			newCss.rel = 'stylesheet';
			newCss.href = cssHref[i];
			newCss.media = 'screen';
			elmHead.appendChild(newCss);
		}

		return cont.replace(AeJSEngine.reCSSAll, '');
	},

	loadScript : function(urls) {
		var loadOne = function(url) {
			var dtd = $.Deferred();
			var node = document.createElement('script');
			node.type = "text/javascript";
			var onload = function() {
				dtd.resolve();
			};
			$(node).load(onload).bind('readystatechange', function() {
				if (node.readyState == 'loaded') {
					onload();
				}
			});
			document.getElementsByTagName('head')[0].appendChild(node);
			node.src = url;
			return dtd.promise();
		};
		var ret = [];
		for ( var i = 0; i < urls.length; i++) {
			ret[i] = loadOne(urls[i]);
		}
		;
		return $.when.apply($, ret);
	},

	procScript : function(iid, vid, cont) {
		var scripts = cont.match(AeJSEngine.reScriptAll) || [];
		var scriptContent = $.map(scripts,
				function(scriptTag) {
					return (scriptTag.match(AeJSEngine.reScriptOne) || [ '',
							'', '' ])[2];
				});
		var scriptDef = $.map(scripts,
				function(scriptTag) {
					return (scriptTag.match(AeJSEngine.reScriptOne) || [ '',
							'', '' ])[1];
				});
		var scriptLanguage = $.map(scriptDef,
				function(scriptTag) {
					return (scriptTag.match(AeJSEngine.reScriptLanguage) || [
							'', '' ])[1];
				});
		var scriptSrc = $.map(scriptDef, function(scriptTag) {
			return (scriptTag.match(AeJSEngine.reScriptSrc) || [ '', '' ])[1];
		});
		var scriptType = $.map(scriptDef, function(scriptTag) {
			return (scriptTag.match(AeJSEngine.reScriptType) || [ '', '' ])[1];
		});
		var scriptCharset = $.map(scriptDef,
				function(scriptTag) {
					return (scriptTag.match(AeJSEngine.reScriptCharset) || [
							'', '' ])[1];
				});

		var elmHead = document.getElementsByTagName("head")[0];
		var elmScripts = elmHead.getElementsByTagName("script");
		var i;
		var j;
		// var newScript;
		var q = false;
		var list_q = [];
		var s = 0;
		for (i = 0; i < scripts.length; i++) {
			if (scriptSrc[i] == "")
				continue;

			for (j = 0; j < elmScripts.length; j++) {
				if (elmScripts[j].src == scriptSrc[i])
					break;
			}

			if (j < elmScripts.length)
				continue;
			q = true;
			list_q[s++] = scriptSrc[i];
			// var newScript = document.createElement('script');
			// if (scriptType[i] != "")
			// newScript.type = scriptType[i];
			// else if (scriptLanguage[i] != "")
			// newScript.type = "text/" + scriptLanguage[i];
			// else
			// newScript.type = "text/javascript";
			// if (scriptCharset[i] != "")
			// newScript.charset = scriptCharset[i];
			// newScript.src = scriptSrc[i];
			// elmHead.appendChild(newScript);
		}
		if (q) {
			AeJSEngine.loadScript(list_q).done(
					function() {
						for (i = 0; i < scriptContent.length; i++)
							try {
								eval(scriptContent[i]
										.replace(
												AeJSEngine.reWinPost,
												'win$._post(' + iid + ', '
														+ vid + ', ').replace(
												AeJSEngine.reWinGet,
												'win$._get(' + iid + ', ' + vid
														+ ', ').replace(
												AeJSEngine.reWinUrl,
												'win$._url(' + iid + ', ' + vid
														+ ', ').replace(
												AeJSEngine.reWinSubmit,
												'win$._submit(' + iid + ', '
														+ vid + ', '));
							} catch (e) {
								alert(e);
								alert(scriptContent[i]);
							}
					});
		}
		if (!q) {
			for (i = 0; i < scriptContent.length; i++)
				try {
					eval(scriptContent[i].replace(AeJSEngine.reWinPost,
							'win$._post(' + iid + ', ' + vid + ', ').replace(
							AeJSEngine.reWinGet,
							'win$._get(' + iid + ', ' + vid + ', ').replace(
							AeJSEngine.reWinUrl,
							'win$._url(' + iid + ', ' + vid + ', ').replace(
							AeJSEngine.reWinSubmit,
							'win$._submit(' + iid + ', ' + vid + ', '));
				} catch (e) {
					alert(e);
					alert(scriptContent[i]);
				}
		}

	},

	closeDialog : function() {
		if (AeJSEngine.dlg == null) {
			AeJSEngine.dlg = $("<div></div>");
			$(document.body).append(AeJSEngine.dlg);
		} else {
			try {
				AeJSEngine.dlg.dialog('destroy');
			} catch (e) {
			}
			AeJSEngine.dlg.empty();
		}
	},

	invokeAfterLoad : function() {
		if (AeJSEngine.afterLoad) {
			try {
				AeJSEngine.afterLoad();
			} catch (e) {
			}
		}
	},

	getLoadContentHandler : function(iid, vid) {
		return function(data, textStatus, jqXHR) {
			var redirect = jqXHR.getResponseHeader('X-Winlet-Redirect');
			if (redirect != null && redirect != "") {
				if (redirect.indexOf("open:") == 0) {
					window.open(redirect.substring(5));
				} else
					window.location.href = redirect;
			}

			var container = null;
			var settings = null;
			var isnew = false;
			var opened = false;

			var uid;
			if (iid == vid)
				uid = '#ap_win_' + iid;
			else
				uid = '#ap_view_' + vid;

			// 对于弹出窗口 ，在ap_win_标签内部建立ap_win_dialog标签用于容纳dialog内容
			if (iid == vid) {
				settings = AeJSEngine.getWinSettings(iid);
				if (settings != null) {
					if (settings.dialog == "yes") {
						container = $("div#ap_win_" + iid + "_dialog");
						if (container.length == 0) {
							container = $("<div id='ap_win_" + iid
									+ "_dialog'></div>");
							isnew = true;
						} else {
							try {
								opened = container.dialog("isOpen");
							} catch (e) {
							}
						}
					}
				}
			}

			if (container == null)
				container = $(uid);

			container.html(AeJSEngine.procStyle(data.replace(
					AeJSEngine.reScriptAll, '').replace(AeJSEngine.reWinPost,
					'win$._post(' + iid + ', ' + vid + ', ')
					.replace(AeJSEngine.reWinGet,
							'win$._get(' + iid + ', ' + vid + ', ').replace(
							AeJSEngine.reWinUrl,
							'win$._url(' + iid + ', ' + vid + ', ').replace(
							AeJSEngine.reWinSubmit,
							'win$._submit(' + iid + ', ' + vid + ', ')));

			// 处理弹出窗口
			if (settings != null && settings.dialog == "yes") {
				var title = AeJSEngine._utf8_decode(jqXHR
						.getResponseHeader('X-Winlet-Title'));
				var content = null;

				try {
					if (title == null || title == '')
						title = container.dialog("option", "title");
					if (title == null)
						title = "";
				} catch (e) {
				}

				if (container[0].innerText == undefined)
					content = $.trim(container[0].innerHTML);
				else
					content = $.trim(container[0].innerText);

				if (content == '' || content == '<div></div>') {
					if (opened)
						try {
							container.dialog('destroy');
						} catch (e) {
						}
				} else {// 显示对话框
					if (!opened) {
						if (isnew)
							$("div#ap_win_" + iid).append(container);

						container
								.dialog({
									title : title,
									autoOpen : true,
									height : 'auto',
									width : 'auto',
									resizable : false,
									modal : true,
									close : function(event, ui) {
										if (settings.close != null) {
											$
													.ajax({
														type : 'POST',
														url : settings.url
																+ "?_a="
																+ iid
																+ "!"
																+ vid
																+ "!"
																+ settings.close,
														data : AeJSEngine
																.mergeParam(
																		iid,
																		$
																				.deparam($(
																						this)
																						.serialize()),
																		{
																			_x : 'y',
																			_pg : window.location.pathname,
																			_purl : window.location.href
																		}),
														success : AeJSEngine
																.getSubmitFormHandler(
																		iid,
																		vid),
														error : AeJSEngine
																.getErrorHandler(
																		iid,
																		vid),
														dataType : "html"
													});
										}
									}
								});
					}
				}
			}

			AeJSEngine.procScript(iid, vid, data);
			AeJSEngine.invokeAfterLoad();
			AeJSEngine.clearLoading(uid);
		};
	},

	loadContent : function(iid, vid, pageRefresh) {
		var uid;

		if (vid == null)
			vid = iid;

		if (iid == vid)
			uid = '#ap_win_' + iid;
		else
			uid = '#ap_view_' + vid;

		if ($(uid).length == 0)
			return;

		AeJSEngine.showLoading(uid);

		$.ajax({
			type : 'POST',
			url : AeJSEngine.getWinSettings(iid).url,
			data : AeJSEngine.mergeParam(iid, {
				_x : 'y',
				_w : iid,
				_wv : vid,
				_pg : window.location.pathname,
				_purl : window.location.href,
				_pr : pageRefresh ? "yes" : "no"
			}),
			success : AeJSEngine.getLoadContentHandler(iid, vid),
			error : AeJSEngine.getErrorHandler(),
			dataType : "html"
		});
	},

	updateWindows : function(iid, wins) {
		if (wins == null || wins == '')
			return;

		var update = wins.split(',');
		var i;
		for (i = 0; i < update.length; i++) {
			try {
				var updateiid = AeJSEngine.hashGroupsByIid[iid].views[update[i]];
				AeJSEngine.loadContent(updateiid);
			} catch (e) {
			}
		}
	},

	validate_clear_all : function(form) {
		$(form).find("span").each(function() {
			if (this.id.substring(0, 13) == "validate_res_") {
				AeJSEngine.validate_clear(this);
			}
		});
	},

	validate_clear : function(result) {
		if (result == undefined || result == null)
			return;

		$(result).html('');
	},

	validating : function(result) {
		if (result == undefined || result == null)
			return;

		$(result).html('<span class="win_validating"></span>');
	},

	validate_success : function(result) {
		if (result == undefined || result == null)
			return;

		$(result).html('<span class="win_valpassed">&nbsp;</span>');
	},

	validate_error : function(result, msg) {
		if (result == undefined || result == null)
			return;

		var failed = $(result).find("div.win_valfailed");
		if (failed.length == 0) {
			$(result).html("<div class='win_valfailed'></div>");
			failed = $(result).find("div.win_valfailed");
		}

		failed.text(msg);
	},

	applyChanges : function(json, form) {
		var changes = null;
		try {
			changes = eval(json);
		} catch (e) {
		}

		if (changes != null) {
			for ( var i = 0; i < changes.length; i++) {
				var result = $(form).find(
						"span#validate_res_"
								+ changes[i].input.replace("[", "\\[").replace(
										"]", "\\]").replace(".", "\\."))[0];
				var inp = $(form).find(
						":input[name='" + changes[i].input + "']")[0];

				if (inp == null) {
					inp = $("#" + changes[i].input)[0];
				}

				if (changes[i].type == 'v') { // 校验结果
					if (result == null)
						continue;

					if (changes[i].message == '')
						AeJSEngine.validate_success(result);
					else {
						AeJSEngine.validate_clear(result);
						AeJSEngine.validate_error(result, changes[i].message);
					}
				} else {
					if (inp == null)
						continue;

					if (changes[i].type == 'u') { // 更新值
						if (inp.type == 'radio')
							$(form).children(
									":input[name='" + changes[i].input
											+ "'][value='" + changes[i].value
											+ "']").attr('checked', 'checked');
						else if (inp.type == 'checkbox')
							inp.checked = changes[i].value;
						else
							$(inp).val(changes[i].value);
					} else if (changes[i].type == 'd') {
						inp.disabled = true;
						if (result != null)
							AeJSEngine.validate_clear(result);
					} else if (changes[i].type == 'e') {
						inp.disabled = false;
					} else if (changes[i].type == 'l') { // 更新列表
						if (inp.type = 'select') {
							$(inp).empty();
							for ( var j = 0; j < changes[i].list.length; j++)
								$(inp).append(
										'<option value="'
												+ changes[i].list[j].id + '">'
												+ changes[i].list[j].name
												+ '</option>');
						}
					}
				}
			}
		}
	},

	getSubmitFormHandler : function(iid, vid, form) {
		return function(data, textStatus, jqXHR) {
			var redirect = jqXHR.getResponseHeader('X-Winlet-Redirect');
			if (redirect != null && redirect != "") {
				if (redirect.indexOf("open:") == 0) {
					window.open(redirect.substring(5));
				} else
					window.location.href = redirect;
			}

			var update = jqXHR.getResponseHeader('X-Winlet-Update');
			var dialog = jqXHR.getResponseHeader('X-Winlet-Dialog');
			var cache = jqXHR.getResponseHeader('X-Winlet-Cache');
			var title = AeJSEngine._utf8_decode(jqXHR
					.getResponseHeader('X-Winlet-Title'));
			var msg = AeJSEngine._utf8_decode(jqXHR
					.getResponseHeader('X-Winlet-Msg'));
			// var ensurevisible =
			// jqXHR.getResponseHeader('X-Winlet-EnsureVisible');

			if (update == "page") {
				location.reload();
				return;
			}

			var uid;
			if (iid == vid)
				uid = '#ap_win_' + iid;
			else
				uid = '#ap_view_' + vid;
			AeJSEngine.clearLoading(uid);

			if (msg != null)
				alert(msg);

			if (form != undefined && dialog != "yes" && "" != data) {
				AeJSEngine.validate_clear_all(form);
				AeJSEngine.applyChanges(data, form);

				if (form.onerror != undefined) {
					try {
						form.onerror(null);
					} catch (e) {
					}
				}
				return;
			}

			if (!(cache == "yes"))
				AeJSEngine.loadContent(iid, vid);

			// Update window or view
			while (update != null && update != "") {
				var iposi = update.indexOf(",");
				var view = update;
				if (iposi != -1) {
					view = update.substr(0, iposi);
					update = update.substr(iposi + 1);
				} else {
					view = update;
					update = "";
				}

				var wid;
				var focus = false;
				if (view.indexOf("!") == 0) {
					focus = true;
					view = view.substring(1);
				}
				if (view.length < 3)
					wid = view;
				else
					wid = parseInt(view.substr(view.length - 2)).toString();

				AeJSEngine.loadContent(wid, view);
			}

			AeJSEngine.closeDialog();

			if (dialog == "yes") {
				AeJSEngine.dlg.append(AeJSEngine.procStyle(data.replace(
						AeJSEngine.reScriptAll, '').replace(
						AeJSEngine.reWinPost,
						'win$._post(' + iid + ', ' + vid + ', ').replace(
						AeJSEngine.reWinGet,
						'win$._get(' + iid + ', ' + vid + ', ').replace(
						AeJSEngine.reWinUrl,
						'win$._url(' + iid + ', ' + vid + ', ').replace(
						AeJSEngine.reWinSubmit,
						'win$._submit(' + iid + ', ' + vid + ', ')));

				if (title == null)
					title = "";

				AeJSEngine.procScript(iid, vid, data);
				$(function() {
					AeJSEngine.dlg.dialog({
						title : title,
						autoOpen : true,
						height : 'auto',
						width : 'auto',
						resizable : false,
						modal : true,
						close : function(event, ui) {
							AeJSEngine.closeDialog()
						}
					});
				});
			}
		};
	},

	getValidateResponseHandler : function(form, name, input) {
		return function(json) {
			AeJSEngine.validate_clear($(form).find(
					"span#validate_res_"
							+ name.replace("[", "\\[").replace("]", "\\]")
									.replace(".", "\\."))[0]);

			AeJSEngine.applyChanges(json, form);

			if (form.onerror != undefined && input != undefined) {
				try {
					form.onerror(input);
				} catch (e) {
				}
			}
		};
	},

	// 简单的错误处理 － 刷新当前页面
	getErrorHandler : function(iid, vid) {
		return function(req, textStatus, errorThrown) {
			// document.location.reload(true);
		};
	},

	/***************************************************************************
	 * 
	 * 扫描<div id="winlet:">标签，在其中生成<div id="ap_win_">标签，并为生成的标签设置settings属性对象。
	 * settings中可以包含以下属性： hashgroup 该window所属的参数组。相同组的window共享hash参数 dialog
	 * 如果值为yes表示用弹出对话框显示窗口 close 对于弹出对话框显示的窗口，关闭窗口时调用的Winlet的方法 url
	 * Winlet的窗口的URL
	 * 
	 **************************************************************************/
	init : function() {
		AeJSEngine.ImgBg.src = $("<div class='winlet_background'></div>").css(
				'background-image').replace(/^url|[\(\)"]/g, '');
		AeJSEngine.ImgLoading.src = $("<div class='winlet_loading'></div>")
				.css('background-image').replace(/^url|[\(\)"]/g, '');
		AeJSEngine.ImgValidating.src = $(
				"<div class='winlet_validating'></div>")
				.css('background-image').replace(/^url|[\(\)"]/g, '');

		AeJSEngine.isStatic = true;
		$('div').filter(function() {
			return this.id.match(AeJSEngine.reWinlet);
		}).each(function(idx) {
			var match = this.id.match(AeJSEngine.reWinlet);
			var hashgroup = AeJSEngine.hashGroupsByUri[match[2]];
			if (hashgroup == null) {
				hashgroup = {
					"idx" : idx,
					views : {}
				};
				AeJSEngine.hashGroupsByUri[match[2]] = hashgroup;
			}
			hashgroup.views[match[3]] = idx;
			AeJSEngine.hashGroupsByIid[idx] = hashgroup;

			var winDiv = $(this).children("div#ap_win_" + idx);
			if (winDiv.length == 0) {
				winDiv = $("<div id='ap_win_" + idx + "'></div>");
				$(this).append(winDiv);
				winDiv[0].settings = {
					"hashgroup" : hashgroup.idx,
					"dialog" : "no",
					"close" : "",
					"url" : "",
					"params" : match[5] == null ? null : $.deparam(match[5])
				};

				if (match.length > 7 && match[7] != null && match[7] != '') {
					var params = match[7].split(',');
					var i;

					for (i = 0; i < params.length; i++) {
						var pmatch = params[i].match(AeJSEngine.reWinletParam);
						winDiv[0].settings[pmatch[1]] = pmatch[2];
					}
				}

				winDiv[0].settings.url = "/" + match[1];
			}

			AeJSEngine.loadContent(idx, idx, true);
		});
	}
};

var win$ = {
	/**
	 * 将参数转成JSON格式。
	 * 
	 * @param params
	 *            要转换的对象，可以为表单名称，表单对象，JSON数据或URL param格式的字符串
	 * @param iid
	 * @param vid
	 * @returns
	 */
	getParams : function(params, iid, vid) {
		if (typeof params == "string") { // 类型为字符串
			if (params.indexOf("=") > 0) // URL param格式字符串
				return $.deparam(params);
			else
				// params为form name
				params = $('form[name="' + params + iid + vid + '"]');
		}

		try {
			if (params.is('form'))
				return $.deparam(params.serialize());
		} catch (e) {
		}

		return params;
	},

	_post : function(iid, vid, action) {
		var params = {};

		for ( var i = 3; i < arguments.length; i++) {
			$.extend(params, win$.getParams(arguments[i], iid, vid));
		}

		$.ajax({
			type : 'POST',
			url : AeJSEngine.getWinSettings(iid).url,
			data : AeJSEngine.mergeParam(iid, params, {
				_x : 'y',
				_w : iid,
				_wv : vid,
				_a : action,
				_pg : window.location.pathname,
				_purl : window.location.href
			}),
			success : AeJSEngine.getSubmitFormHandler(iid, vid),
			error : AeJSEngine.getErrorHandler(iid, vid),
			dataType : "html"
		});
	},

	_get : function(iid, vid, update) {
		var params = {};

		for ( var i = 3; i < arguments.length; i++) {
			$.extend(params, win$.getParams(arguments[i], iid, vid));
		}

		AeJSEngine.setHash(iid, params);
		AeJSEngine.loadContent(iid);
		AeJSEngine.updateWindows(iid, update);
	},

	_url : function(iid, vid, action) {
		var params = {};

		for ( var i = 3; i < arguments.length; i++)
			$.extend(params, win$.getParams(arguments[i], iid, vid));

		return AeJSEngine.getWinSettings(iid).url + "?"
				+ AeJSEngine.mergeParam(iid, params, {
					_x : 'y',
					_w : iid,
					_wv : vid,
					_a : action,
					_pg : window.location.pathname,
					_purl : window.location.href
				});
	},

	reAction : new RegExp('^(.*)!(.*)!(.*)$'),

	_submit : function(iid, vid, form, action) {
		var f = $('form[name="' + form + iid + vid + '"]');
		if (f.length != 1)
			return;

		if (action != null && action != '') {
			if (!f.attr('action').match(win$.reAction))
				return;
			f.attr('action', f.attr('action').replace(win$.reAction,
					"$1!$2!" + action));
		}

		var params = {};
		for ( var i = 4; i < arguments.length; i++) {
			$.extend(params, win$.getParams(arguments[i], iid, vid));
		}

		for ( var key in params) {
			f.find("input[name=" + key + "]").attr("value", params[key]);
		}

		f.submit();
	}
};

$(window).hashchange(function() {
	if (AeJSEngine.detectHashChange)
		AeJSEngine.init();
	else
		AeJSEngine.detectHashChange = true;
});

$(function() {
	AeJSEngine.init();
});
