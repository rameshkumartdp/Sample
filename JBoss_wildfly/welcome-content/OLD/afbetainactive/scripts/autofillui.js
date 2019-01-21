!function(t) {
	"function" == typeof define && define.amd ? define(["jquery"], t) : t(jQuery)
}(function(t) {
	t.ui = t.ui || {};
	var e = (t.ui.version = "1.12.0", 0),
		i = Array.prototype.slice;
	t.cleanData = function(e) {
		return function(i) {
			var s, n, o;
			for (o = 0; null != (n = i[o]); o++) try {
				s = t._data(n, "events"), s && s.remove && t(n).triggerHandler("remove")
			} catch (a) {}
			e(i)
		}
	}(t.cleanData), t.widget = function(e, i, s) {
		var n, o, a, r = {},
			l = e.split(".")[0];
		e = e.split(".")[1];
		var u = l + "-" + e;
		return s || (s = i, i = t.Widget), t.isArray(s) && (s = t.extend.apply(null, [{}].concat(s))), t.expr[":"][u.toLowerCase()] = function(e) {
			return !!t.data(e, u)
		}, t[l] = t[l] || {}, n = t[l][e], o = t[l][e] = function(t, e) {
			return this._createWidget ? void(arguments.length && this._createWidget(t, e)) : new o(t, e)
		}, t.extend(o, n, {
			version: s.version,
			_proto: t.extend({}, s),
			_childConstructors: []
		}), a = new i, a.options = t.widget.extend({}, a.options), t.each(s, function(e, s) {
			return t.isFunction(s) ? void(r[e] = function() {
				function t() {
					return i.prototype[e].apply(this, arguments)
				}
				function n(t) {
					return i.prototype[e].apply(this, t)
				}
				return function() {
					var e, i = this._super,
						o = this._superApply;
					return this._super = t, this._superApply = n, e = s.apply(this, arguments), this._super = i, this._superApply = o, e
				}
			}()) : void(r[e] = s)
		}), o.prototype = t.widget.extend(a, {
			widgetEventPrefix: n ? a.widgetEventPrefix || e : e
		}, r, {
			constructor: o,
			namespace: l,
			widgetName: e,
			widgetFullName: u
		}), n ? (t.each(n._childConstructors, function(e, i) {
			var s = i.prototype;
			t.widget(s.namespace + "." + s.widgetName, o, i._proto)
		}), delete n._childConstructors) : i._childConstructors.push(o), t.widget.bridge(e, o), o
	}, t.widget.extend = function(e) {
		for (var s, n, o = i.call(arguments, 1), a = 0, r = o.length; r > a; a++)
			for (s in o[a]) n = o[a][s], o[a].hasOwnProperty(s) && void 0 !== n && (t.isPlainObject(n) ? e[s] = t.isPlainObject(e[s]) ? t.widget.extend({}, e[s], n) : t.widget.extend({}, n) : e[s] = n);
		return e
	}, t.widget.bridge = function(e, s) {
		var n = s.prototype.widgetFullName || e;
		t.fn[e] = function(o) {
			var a = "string" == typeof o,
				r = i.call(arguments, 1),
				l = this;
			return a ? this.each(function() {
				var i, s = t.data(this, n);
				return "instance" === o ? (l = s, !1) : s ? t.isFunction(s[o]) && "_" !== o.charAt(0) ? (i = s[o].apply(s, r), i !== s && void 0 !== i ? (l = i && i.jquery ? l.pushStack(i.get()) : i, !1) : void 0) : t.error("no such method '" + o + "' for " + e + " widget instance") : t.error("cannot call methods on " + e + " prior to initialization; attempted to call method '" + o + "'")
			}) : (r.length && (o = t.widget.extend.apply(null, [o].concat(r))), this.each(function() {
				var e = t.data(this, n);
				e ? (e.option(o || {}), e._init && e._init()) : t.data(this, n, new s(o, this))
			})), l
		}
	}, t.Widget = function() {}, t.Widget._childConstructors = [], t.Widget.prototype = {
		widgetName: "widget",
		widgetEventPrefix: "",
		defaultElement: "<div>",
		options: {
			classes: {},
			disabled: !1,
			create: null
		},
		_createWidget: function(i, s) {
			s = t(s || this.defaultElement || this)[0], this.element = t(s), this.uuid = e++, this.eventNamespace = "." + this.widgetName + this.uuid, this.bindings = t(), this.hoverable = t(), this.focusable = t(), this.classesElementLookup = {}, s !== this && (t.data(s, this.widgetFullName, this), this._on(!0, this.element, {
				remove: function(t) {
					t.target === s && this.destroy()
				}
			}), this.document = t(s.style ? s.ownerDocument : s.document || s), this.window = t(this.document[0].defaultView || this.document[0].parentWindow)), this.options = t.widget.extend({}, this.options, this._getCreateOptions(), i), this._create(), this.options.disabled && this._setOptionDisabled(this.options.disabled), this._trigger("create", null, this._getCreateEventData()), this._init()
		},
		_getCreateOptions: function() {
			return {}
		},
		_getCreateEventData: t.noop,
		_create: t.noop,
		_init: t.noop,
		destroy: function() {
			var e = this;
			this._destroy(), t.each(this.classesElementLookup, function(t, i) {
				e._removeClass(i, t)
			}), this.element.off(this.eventNamespace).removeData(this.widgetFullName), this.widget().off(this.eventNamespace).removeAttr("aria-disabled"), this.bindings.off(this.eventNamespace)
		},
		_destroy: t.noop,
		widget: function() {
			return this.element
		},
		option: function(e, i) {
			var s, n, o, a = e;
			if (0 === arguments.length) return t.widget.extend({}, this.options);
			if ("string" == typeof e)
				if (a = {}, s = e.split("."), e = s.shift(), s.length) {
					for (n = a[e] = t.widget.extend({}, this.options[e]), o = 0; o < s.length - 1; o++) n[s[o]] = n[s[o]] || {}, n = n[s[o]];
					if (e = s.pop(), 1 === arguments.length) return void 0 === n[e] ? null : n[e];
					n[e] = i
				} else {
					if (1 === arguments.length) return void 0 === this.options[e] ? null : this.options[e];
					a[e] = i
				}
			return this._setOptions(a), this
		},
		_setOptions: function(t) {
			var e;
			for (e in t) this._setOption(e, t[e]);
			return this
		},
		_setOption: function(t, e) {
			return "classes" === t && this._setOptionClasses(e), this.options[t] = e, "disabled" === t && this._setOptionDisabled(e), this
		},
		_setOptionClasses: function(e) {
			var i, s, n;
			for (i in e) n = this.classesElementLookup[i], e[i] !== this.options.classes[i] && n && n.length && (s = t(n.get()), this._removeClass(n, i), s.addClass(this._classes({
				element: s,
				keys: i,
				classes: e,
				add: !0
			})))
		},
		_setOptionDisabled: function(t) {
			this._toggleClass(this.widget(), this.widgetFullName + "-disabled", null, !!t), t && (this._removeClass(this.hoverable, null, "ui-state-hover"), this._removeClass(this.focusable, null, "ui-state-focus"))
		},
		enable: function() {
			return this._setOptions({
				disabled: !1
			})
		},
		disable: function() {
			return this._setOptions({
				disabled: !0
			})
		},
		_classes: function(e) {
			function i(i, o) {
				var a, r;
				for (r = 0; r < i.length; r++) a = n.classesElementLookup[i[r]] || t(), a = t(e.add ? t.unique(a.get().concat(e.element.get())) : a.not(e.element).get()), n.classesElementLookup[i[r]] = a, s.push(i[r]), o && e.classes[i[r]] && s.push(e.classes[i[r]])
			}
			var s = [],
				n = this;
			return e = t.extend({
				element: this.element,
				classes: this.options.classes || {}
			}, e), e.keys && i(e.keys.match(/\S+/g) || [], !0), e.extra && i(e.extra.match(/\S+/g) || []), s.join(" ")
		},
		_removeClass: function(t, e, i) {
			return this._toggleClass(t, e, i, !1)
		},
		_addClass: function(t, e, i) {
			return this._toggleClass(t, e, i, !0)
		},
		_toggleClass: function(t, e, i, s) {
			s = "boolean" == typeof s ? s : i;
			var n = "string" == typeof t || null === t,
				o = {
					extra: n ? e : i,
					keys: n ? t : e,
					element: n ? this.element : t,
					add: s
				};
			return o.element.toggleClass(this._classes(o), s), this
		},
		_on: function(e, i, s) {
			var n, o = this;
			"boolean" != typeof e && (s = i, i = e, e = !1), s ? (i = n = t(i), this.bindings = this.bindings.add(i)) : (s = i, i = this.element, n = this.widget()), t.each(s, function(s, a) {
				function r() {
					return e || o.options.disabled !== !0 && !t(this).hasClass("ui-state-disabled") ? ("string" == typeof a ? o[a] : a).apply(o, arguments) : void 0
				}
				"string" != typeof a && (r.guid = a.guid = a.guid || r.guid || t.guid++);
				var l = s.match(/^([\w:-]*)\s*(.*)$/),
					u = l[1] + o.eventNamespace,
					h = l[2];
				h ? n.on(u, h, r) : i.on(u, r)
			})
		},
		_off: function(e, i) {
			i = (i || "").split(" ").join(this.eventNamespace + " ") + this.eventNamespace, e.off(i).off(i), this.bindings = t(this.bindings.not(e).get()), this.focusable = t(this.focusable.not(e).get()), this.hoverable = t(this.hoverable.not(e).get())
		},
		_delay: function(t, e) {
			function i() {
				return ("string" == typeof t ? s[t] : t).apply(s, arguments)
			}
			var s = this;
			return setTimeout(i, e || 0)
		},
		_hoverable: function(e) {
			this.hoverable = this.hoverable.add(e), this._on(e, {
				mouseenter: function(e) {
					this._addClass(t(e.currentTarget), null, "ui-state-hover")
				},
				mouseleave: function(e) {
					this._removeClass(t(e.currentTarget), null, "ui-state-hover")
				}
			})
		},
		_focusable: function(e) {
			this.focusable = this.focusable.add(e), this._on(e, {
				focusin: function(e) {
					this._addClass(t(e.currentTarget), null, "ui-state-focus")
				},
				focusout: function(e) {
					this._removeClass(t(e.currentTarget), null, "ui-state-focus")
				}
			})
		},
		_trigger: function(e, i, s) {
			var n, o, a = this.options[e];
			if (s = s || {}, i = t.Event(i), i.type = (e === this.widgetEventPrefix ? e : this.widgetEventPrefix + e).toLowerCase(), i.target = this.element[0], o = i.originalEvent)
				for (n in o) n in i || (i[n] = o[n]);
			return this.element.trigger(i, s), !(t.isFunction(a) && a.apply(this.element[0], [i].concat(s)) === !1 || i.isDefaultPrevented())
		}
	}, t.each({
		show: "fadeIn",
		hide: "fadeOut"
	}, function(e, i) {
		t.Widget.prototype["_" + e] = function(s, n, o) {
			"string" == typeof n && (n = {
				effect: n
			});
			var a, r = n ? n === !0 || "number" == typeof n ? i : n.effect || i : e;
			n = n || {}, "number" == typeof n && (n = {
				duration: n
			}), a = !t.isEmptyObject(n), n.complete = o, n.delay && s.delay(n.delay), a && t.effects && t.effects.effect[r] ? s[e](n) : r !== e && s[r] ? s[r](n.duration, n.easing, o) : s.queue(function(i) {
				t(this)[e](), o && o.call(s[0]), i()
			})
		}
	});
	t.widget;
	! function() {
		function e(t, e, i) {
			return [parseFloat(t[0]) * (f.test(t[0]) ? e / 100 : 1), parseFloat(t[1]) * (f.test(t[1]) ? i / 100 : 1)]
		}
		function i(e, i) {
			return parseInt(t.css(e, i), 10) || 0
		}
		function s(e) {
			var i = e[0];
			return 9 === i.nodeType ? {
				width: e.width(),
				height: e.height(),
				offset: {
					top: 0,
					left: 0
				}
			} : t.isWindow(i) ? {
				width: e.width(),
				height: e.height(),
				offset: {
					top: e.scrollTop(),
					left: e.scrollLeft()
				}
			} : i.preventDefault ? {
				width: 0,
				height: 0,
				offset: {
					top: i.pageY,
					left: i.pageX
				}
			} : {
				width: e.outerWidth(),
				height: e.outerHeight(),
				offset: e.offset()
			}
		}
		var n, o, a = Math.max,
			r = Math.abs,
			l = Math.round,
			u = /left|center|right/,
			h = /top|center|bottom/,
			c = /[\+\-]\d+(\.[\d]+)?%?/,
			d = /^\w+/,
			f = /%$/,
			m = t.fn.position;
		o = function() {
			var e = t("<div>").css("position", "absolute").appendTo("body").offset({
					top: 1.5,
					left: 1.5
				}),
				i = 1.5 === e.offset().top;
			return e.remove(), o = function() {
				return i
			}, i
		}, t.position = {
			scrollbarWidth: function() {
				if (void 0 !== n) return n;
				var e, i, s = t("<div style='display:block;position:absolute;width:50px;height:50px;overflow:hidden;'><div style='height:100px;width:auto;'></div></div>"),
					o = s.children()[0];
				return t("body").append(s), e = o.offsetWidth, s.css("overflow", "scroll"), i = o.offsetWidth, e === i && (i = s[0].clientWidth), s.remove(), n = e - i
			},
			getScrollInfo: function(e) {
				var i = e.isWindow || e.isDocument ? "" : e.element.css("overflow-x"),
					s = e.isWindow || e.isDocument ? "" : e.element.css("overflow-y"),
					n = "scroll" === i || "auto" === i && e.width < e.element[0].scrollWidth,
					o = "scroll" === s || "auto" === s && e.height < e.element[0].scrollHeight;
				return {
					width: o ? t.position.scrollbarWidth() : 0,
					height: n ? t.position.scrollbarWidth() : 0
				}
			},
			getWithinInfo: function(e) {
				var i = t(e || window),
					s = t.isWindow(i[0]),
					n = !!i[0] && 9 === i[0].nodeType,
					o = !s && !n;
				return {
					element: i,
					isWindow: s,
					isDocument: n,
					offset: o ? t(e).offset() : {
						left: 0,
						top: 0
					},
					scrollLeft: i.scrollLeft(),
					scrollTop: i.scrollTop(),
					width: i.outerWidth(),
					height: i.outerHeight()
				}
			}
		}, t.fn.position = function(n) {
			if (!n || !n.of) return m.apply(this, arguments);
			n = t.extend({}, n);
			var f, p, v, g, _, y, b = t(n.of),
				w = t.position.getWithinInfo(n.within),
				x = t.position.getScrollInfo(w),
				C = (n.collision || "flip").split(" "),
				E = {};
			return y = s(b), b[0].preventDefault && (n.at = "left top"), p = y.width, v = y.height, g = y.offset, _ = t.extend({}, g), t.each(["my", "at"], function() {
				var t, e, i = (n[this] || "").split(" ");
				1 === i.length && (i = u.test(i[0]) ? i.concat(["center"]) : h.test(i[0]) ? ["center"].concat(i) : ["center", "center"]), i[0] = u.test(i[0]) ? i[0] : "center", i[1] = h.test(i[1]) ? i[1] : "center", t = c.exec(i[0]), e = c.exec(i[1]), E[this] = [t ? t[0] : 0, e ? e[0] : 0], n[this] = [d.exec(i[0])[0], d.exec(i[1])[0]]
			}), 1 === C.length && (C[1] = C[0]), "right" === n.at[0] ? _.left += p : "center" === n.at[0] && (_.left += p / 2), "bottom" === n.at[1] ? _.top += v : "center" === n.at[1] && (_.top += v / 2), f = e(E.at, p, v), _.left += f[0], _.top += f[1], this.each(function() {
				var s, u, h = t(this),
					c = h.outerWidth(),
					d = h.outerHeight(),
					m = i(this, "marginLeft"),
					y = i(this, "marginTop"),
					k = c + m + i(this, "marginRight") + x.width,
					T = d + y + i(this, "marginBottom") + x.height,
					W = t.extend({}, _),
					A = e(E.my, h.outerWidth(), h.outerHeight());
				"right" === n.my[0] ? W.left -= c : "center" === n.my[0] && (W.left -= c / 2), "bottom" === n.my[1] ? W.top -= d : "center" === n.my[1] && (W.top -= d / 2), W.left += A[0], W.top += A[1], o() || (W.left = l(W.left), W.top = l(W.top)), s = {
					marginLeft: m,
					marginTop: y
				}, t.each(["left", "top"], function(e, i) {
					t.ui.position[C[e]] && t.ui.position[C[e]][i](W, {
						targetWidth: p,
						targetHeight: v,
						elemWidth: c,
						elemHeight: d,
						collisionPosition: s,
						collisionWidth: k,
						collisionHeight: T,
						offset: [f[0] + A[0], f[1] + A[1]],
						my: n.my,
						at: n.at,
						within: w,
						elem: h
					})
				}), n.using && (u = function(t) {
					var e = g.left - W.left,
						i = e + p - c,
						s = g.top - W.top,
						o = s + v - d,
						l = {
							target: {
								element: b,
								left: g.left,
								top: g.top,
								width: p,
								height: v
							},
							element: {
								element: h,
								left: W.left,
								top: W.top,
								width: c,
								height: d
							},
							horizontal: 0 > i ? "left" : e > 0 ? "right" : "center",
							vertical: 0 > o ? "top" : s > 0 ? "bottom" : "middle"
						};
					c > p && r(e + i) < p && (l.horizontal = "center"), d > v && r(s + o) < v && (l.vertical = "middle"), a(r(e), r(i)) > a(r(s), r(o)) ? l.important = "horizontal" : l.important = "vertical", n.using.call(this, t, l)
				}), h.offset(t.extend(W, {
					using: u
				}))
			})
		}, t.ui.position = {
			fit: {
				left: function(t, e) {
					var i, s = e.within,
						n = s.isWindow ? s.scrollLeft : s.offset.left,
						o = s.width,
						r = t.left - e.collisionPosition.marginLeft,
						l = n - r,
						u = r + e.collisionWidth - o - n;
					e.collisionWidth > o ? l > 0 && 0 >= u ? (i = t.left + l + e.collisionWidth - o - n, t.left += l - i) : u > 0 && 0 >= l ? t.left = n : l > u ? t.left = n + o - e.collisionWidth : t.left = n : l > 0 ? t.left += l : u > 0 ? t.left -= u : t.left = a(t.left - r, t.left)
				},
				top: function(t, e) {
					var i, s = e.within,
						n = s.isWindow ? s.scrollTop : s.offset.top,
						o = e.within.height,
						r = t.top - e.collisionPosition.marginTop,
						l = n - r,
						u = r + e.collisionHeight - o - n;
					e.collisionHeight > o ? l > 0 && 0 >= u ? (i = t.top + l + e.collisionHeight - o - n, t.top += l - i) : u > 0 && 0 >= l ? t.top = n : l > u ? t.top = n + o - e.collisionHeight : t.top = n : l > 0 ? t.top += l : u > 0 ? t.top -= u : t.top = a(t.top - r, t.top)
				}
			},
			flip: {
				left: function(t, e) {
					var i, s, n = e.within,
						o = n.offset.left + n.scrollLeft,
						a = n.width,
						l = n.isWindow ? n.scrollLeft : n.offset.left,
						u = t.left - e.collisionPosition.marginLeft,
						h = u - l,
						c = u + e.collisionWidth - a - l,
						d = "left" === e.my[0] ? -e.elemWidth : "right" === e.my[0] ? e.elemWidth : 0,
						f = "left" === e.at[0] ? e.targetWidth : "right" === e.at[0] ? -e.targetWidth : 0,
						m = -2 * e.offset[0];
					0 > h ? (i = t.left + d + f + m + e.collisionWidth - a - o, (0 > i || i < r(h)) && (t.left += d + f + m)) : c > 0 && (s = t.left - e.collisionPosition.marginLeft + d + f + m - l, (s > 0 || r(s) < c) && (t.left += d + f + m))
				},
				top: function(t, e) {
					var i, s, n = e.within,
						o = n.offset.top + n.scrollTop,
						a = n.height,
						l = n.isWindow ? n.scrollTop : n.offset.top,
						u = t.top - e.collisionPosition.marginTop,
						h = u - l,
						c = u + e.collisionHeight - a - l,
						d = "top" === e.my[1],
						f = d ? -e.elemHeight : "bottom" === e.my[1] ? e.elemHeight : 0,
						m = "top" === e.at[1] ? e.targetHeight : "bottom" === e.at[1] ? -e.targetHeight : 0,
						p = -2 * e.offset[1];
					0 > h ? (s = t.top + f + m + p + e.collisionHeight - a - o, (0 > s || s < r(h)) && (t.top += f + m + p)) : c > 0 && (i = t.top - e.collisionPosition.marginTop + f + m + p - l, (i > 0 || r(i) < c) && (t.top += f + m + p))
				}
			},
			flipfit: {
				left: function() {
					t.ui.position.flip.left.apply(this, arguments), t.ui.position.fit.left.apply(this, arguments)
				},
				top: function() {
					t.ui.position.flip.top.apply(this, arguments), t.ui.position.fit.top.apply(this, arguments)
				}
			}
		}
	}();
	t.ui.position, t.ui.keyCode = {
		BACKSPACE: 8,
		COMMA: 188,
		DELETE: 46,
		DOWN: 40,
		END: 35,
		ENTER: 13,
		ESCAPE: 27,
		HOME: 36,
		LEFT: 37,
		PAGE_DOWN: 34,
		PAGE_UP: 33,
		PERIOD: 190,
		RIGHT: 39,
		SPACE: 32,
		TAB: 9,
		UP: 38
	}, t.fn.extend({
		uniqueId: function() {
			var t = 0;
			return function() {
				return this.each(function() {
					this.id || (this.id = "ui-id-" + ++t)
				})
			}
		}(),
		removeUniqueId: function() {
			return this.each(function() {
				/^ui-id-\d+$/.test(this.id) && t(this).removeAttr("id")
			})
		}
	}), t.ui.safeActiveElement = function(t) {
		var e;
		try {
			e = t.activeElement
		} catch (i) {
			e = t.body
		}
		return e || (e = t.body), e.nodeName || (e = t.body), e
	}, t.widget("ui.menu", {
		version: "1.12.0",
		defaultElement: "<ul>",
		delay: 300,
		options: {
			icons: {
				submenu: "ui-icon-caret-1-e"
			},
			items: "> *",
			menus: "ul",
			position: {
				my: "left top",
				at: "right top"
			},
			role: "menu",
			blur: null,
			focus: null,
			select: null
		},
		_create: function() {
			this.activeMenu = this.element, this.mouseHandled = !1, this.element.uniqueId().attr({
				role: this.options.role,
				tabIndex: 0
			}), this._addClass("ui-menu", "ui-widget ui-widget-content"), this._on({
				"mousedown .ui-menu-item": function(t) {
					t.preventDefault()
				},
				"click .ui-menu-item": function(e) {
					var i = t(e.target),
						s = t(t.ui.safeActiveElement(this.document[0]));
					!this.mouseHandled && i.not(".ui-state-disabled").length && (this.select(e), e.isPropagationStopped() || (this.mouseHandled = !0), i.has(".ui-menu").length ? this.expand(e) : !this.element.is(":focus") && s.closest(".ui-menu").length && (this.element.trigger("focus", [!0]), this.active && 1 === this.active.parents(".ui-menu").length && clearTimeout(this.timer)))
				},
				"mouseenter .ui-menu-item": function(e) {
					if (!this.previousFilter) {
						var i = t(e.target).closest(".ui-menu-item"),
							s = t(e.currentTarget);
						i[0] === s[0] && (this._removeClass(s.siblings().children(".ui-state-active"), null, "ui-state-active"), this.focus(e, s))
					}
				},
				mouseleave: "collapseAll",
				"mouseleave .ui-menu": "collapseAll",
				focus: function(t, e) {
					var i = this.active || this.element.find(this.options.items).eq(0);
					e || this.focus(t, i)
				},
				blur: function(e) {
					this._delay(function() {
						var i = !t.contains(this.element[0], t.ui.safeActiveElement(this.document[0]));
						i && this.collapseAll(e)
					})
				},
				keydown: "_keydown"
			}), this.refresh(), this._on(this.document, {
				click: function(t) {
					this._closeOnDocumentClick(t) && this.collapseAll(t), this.mouseHandled = !1
				}
			})
		},
		_destroy: function() {
			var e = this.element.find(".ui-menu-item").removeAttr("role aria-disabled"),
				i = e.children(".ui-menu-item-wrapper").removeUniqueId().removeAttr("tabIndex role aria-haspopup");
			this.element.removeAttr("aria-activedescendant").find(".ui-menu").addBack().removeAttr("role aria-labelledby aria-expanded aria-hidden aria-disabled tabIndex").removeUniqueId().show(), i.children().each(function() {
				var e = t(this);
				e.data("ui-menu-submenu-caret") && e.remove()
			})
		},
		_keydown: function(e) {
			var i, s, n, o, a = !0;
			switch (e.keyCode) {
				case t.ui.keyCode.PAGE_UP:
					this.previousPage(e);
					break;
				case t.ui.keyCode.PAGE_DOWN:
					this.nextPage(e);
					break;
				case t.ui.keyCode.HOME:
					this._move("first", "first", e);
					break;
				case t.ui.keyCode.END:
					this._move("last", "last", e);
					break;
				case t.ui.keyCode.UP:
					this.previous(e);
					break;
				case t.ui.keyCode.DOWN:
					this.next(e);
					break;
				case t.ui.keyCode.LEFT:
					this.collapse(e);
					break;
				case t.ui.keyCode.RIGHT:
					this.active && !this.active.is(".ui-state-disabled") && this.expand(e);
					break;
				case t.ui.keyCode.ENTER:
				case t.ui.keyCode.SPACE:
					this._activate(e);
					break;
				case t.ui.keyCode.ESCAPE:
					this.collapse(e);
					break;
				default:
					a = !1, s = this.previousFilter || "", n = String.fromCharCode(e.keyCode), o = !1, clearTimeout(this.filterTimer), n === s ? o = !0 : n = s + n, i = this._filterMenuItems(n), i = o && -1 !== i.index(this.active.next()) ? this.active.nextAll(".ui-menu-item") : i, i.length || (n = String.fromCharCode(e.keyCode), i = this._filterMenuItems(n)), i.length ? (this.focus(e, i), this.previousFilter = n, this.filterTimer = this._delay(function() {
						delete this.previousFilter
					}, 1e3)) : delete this.previousFilter
			}
			a && e.preventDefault()
		},
		_activate: function(t) {
			this.active && !this.active.is(".ui-state-disabled") && (this.active.children("[aria-haspopup='true']").length ? this.expand(t) : this.select(t))
		},
		refresh: function() {
			var e, i, s, n, o, a = this,
				r = this.options.icons.submenu,
				l = this.element.find(this.options.menus);
			this._toggleClass("ui-menu-icons", null, !!this.element.find(".ui-icon").length), s = l.filter(":not(.ui-menu)").hide().attr({
				role: this.options.role,
				"aria-hidden": "true",
				"aria-expanded": "false"
			}).each(function() {
				var e = t(this),
					i = e.prev(),
					s = t("<span>").data("ui-menu-submenu-caret", !0);
				a._addClass(s, "ui-menu-icon", "ui-icon " + r), i.attr("aria-haspopup", "true").prepend(s), e.attr("aria-labelledby", i.attr("id"))
			}), this._addClass(s, "ui-menu", "ui-widget ui-widget-content ui-front"), e = l.add(this.element), i = e.find(this.options.items), i.not(".ui-menu-item").each(function() {
				var e = t(this);
				a._isDivider(e) && a._addClass(e, "ui-menu-divider", "ui-widget-content")
			}), n = i.not(".ui-menu-item, .ui-menu-divider"), o = n.children().not(".ui-menu").uniqueId().attr({
				tabIndex: -1,
				role: this._itemRole()
			}), this._addClass(n, "ui-menu-item")._addClass(o, "ui-menu-item-wrapper"), i.filter(".ui-state-disabled").attr("aria-disabled", "true"), this.active && !t.contains(this.element[0], this.active[0]) && this.blur()
		},
		_itemRole: function() {
			return {
				menu: "menuitem",
				listbox: "option"
			}[this.options.role]
		},
		_setOption: function(t, e) {
			if ("icons" === t) {
				var i = this.element.find(".ui-menu-icon");
				this._removeClass(i, null, this.options.icons.submenu)._addClass(i, null, e.submenu)
			}
			this._super(t, e)
		},
		_setOptionDisabled: function(t) {
			this._super(t), this.element.attr("aria-disabled", String(t)), this._toggleClass(null, "ui-state-disabled", !!t)
		},
		focus: function(t, e) {
			var i, s;
			this.blur(t, t && "focus" === t.type), this._scrollIntoView(e), this.active = e.first(), s = this.active.children(".ui-menu-item-wrapper"), this._addClass(this.active, null, "ui-state-active"), this.options.role && this.element.attr("aria-activedescendant", this.active.attr("id")), this._addClass(this.active, null, "ui-state-active"), t && "keydown" === t.type ? this._close() : this.timer = this._delay(function() {
				this._close()
			}, this.delay), i = e.children(".ui-menu"), i.length && t && /^mouse/.test(t.type) && this._startOpening(i), this.activeMenu = e.parent(), this._trigger("focus", t, {
				item: e
			})
		},
		_scrollIntoView: function(e) {
			var i, s, n, o, a, r;
			this._hasScroll() && (i = parseFloat(t.css(this.activeMenu[0], "borderTopWidth")) || 0, s = parseFloat(t.css(this.activeMenu[0], "paddingTop")) || 0, n = e.offset().top - this.activeMenu.offset().top - i - s, o = this.activeMenu.scrollTop(), a = this.activeMenu.height(), r = e.outerHeight(), 0 > n ? this.activeMenu.scrollTop(o + n) : n + r > a && this.activeMenu.scrollTop(o + n - a + r))
		},
		blur: function(t, e) {
			e || clearTimeout(this.timer), this.active && (this._removeClass(this.active, null, "ui-state-active"), this._trigger("blur", t, {
				item: this.active
			}), this.active = null)
		},
		_startOpening: function(t) {
			clearTimeout(this.timer), "true" === t.attr("aria-hidden") && (this.timer = this._delay(function() {
				this._close(), this._open(t)
			}, this.delay))
		},
		_open: function(e) {
			var i = t.extend({
				of: this.active
			}, this.options.position);
			clearTimeout(this.timer), this.element.find(".ui-menu").not(e.parents(".ui-menu")).hide().attr("aria-hidden", "true"), e.show().removeAttr("aria-hidden").attr("aria-expanded", "true").position(i)
		},
		collapseAll: function(e, i) {
			clearTimeout(this.timer), this.timer = this._delay(function() {
				var s = i ? this.element : t(e && e.target).closest(this.element.find(".ui-menu"));
				s.length || (s = this.element), this._close(s), this.blur(e), this._removeClass(s.find(".ui-state-active"), null, "ui-state-active"), this.activeMenu = s
			}, this.delay)
		},
		_close: function(t) {
			t || (t = this.active ? this.active.parent() : this.element), t.find(".ui-menu").hide().attr("aria-hidden", "true").attr("aria-expanded", "false")
		},
		_closeOnDocumentClick: function(e) {
			return !t(e.target).closest(".ui-menu").length
		},
		_isDivider: function(t) {
			return !/[^\-\u2014\u2013\s]/.test(t.text())
		},
		collapse: function(t) {
			var e = this.active && this.active.parent().closest(".ui-menu-item", this.element);
			e && e.length && (this._close(), this.focus(t, e))
		},
		expand: function(t) {
			var e = this.active && this.active.children(".ui-menu ").find(this.options.items).first();
			e && e.length && (this._open(e.parent()), this._delay(function() {
				this.focus(t, e)
			}))
		},
		next: function(t) {
			this._move("next", "first", t)
		},
		previous: function(t) {
			this._move("prev", "last", t)
		},
		isFirstItem: function() {
			return this.active && !this.active.prevAll(".ui-menu-item").length
		},
		isLastItem: function() {
			return this.active && !this.active.nextAll(".ui-menu-item").length
		},
		_move: function(t, e, i) {
			var s;
			this.active && (s = "first" === t || "last" === t ? this.active["first" === t ? "prevAll" : "nextAll"](".ui-menu-item").eq(-1) : this.active[t + "All"](".ui-menu-item").eq(0)), s && s.length && this.active || (s = this.activeMenu.find(this.options.items)[e]()), this.focus(i, s)
		},
		nextPage: function(e) {
			var i, s, n;
			return this.active ? void(this.isLastItem() || (this._hasScroll() ? (s = this.active.offset().top, n = this.element.height(), this.active.nextAll(".ui-menu-item").each(function() {
				return i = t(this), i.offset().top - s - n < 0
			}), this.focus(e, i)) : this.focus(e, this.activeMenu.find(this.options.items)[this.active ? "last" : "first"]()))) : void this.next(e)
		},
		previousPage: function(e) {
			var i, s, n;
			return this.active ? void(this.isFirstItem() || (this._hasScroll() ? (s = this.active.offset().top, n = this.element.height(), this.active.prevAll(".ui-menu-item").each(function() {
				return i = t(this), i.offset().top - s + n > 0
			}), this.focus(e, i)) : this.focus(e, this.activeMenu.find(this.options.items).first()))) : void this.next(e)
		},
		_hasScroll: function() {
			return this.element.outerHeight() < this.element.prop("scrollHeight")
		},
		select: function(e) {
			this.active = this.active || t(e.target).closest(".ui-menu-item");
			var i = {
				item: this.active
			};
			this.active.has(".ui-menu").length || this.collapseAll(e, !0), this._trigger("select", e, i)
		},
		_filterMenuItems: function(e) {
			var i = e.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&"),
				s = new RegExp("^" + i, "i");
			return this.activeMenu.find(this.options.items).filter(".ui-menu-item").filter(function() {
				return s.test(t.trim(t(this).children(".ui-menu-item-wrapper").text()))
			})
		}
	});
	t.widget("ui.autocomplete", {
		version: "1.12.0",
		defaultElement: "<input>",
		options: {
			appendTo: null,
			autoFocus: !1,
			delay: 300,
			minLength: 1,
			position: {
				my: "left top",
				at: "left bottom",
				collision: "none"
			},
			source: null,
			change: null,
			close: null,
			focus: null,
			open: null,
			response: null,
			search: null,
			select: null
		},
		requestIndex: 0,
		pending: 0,
		_create: function() {
			var e, i, s, n = this.element[0].nodeName.toLowerCase(),
				o = "textarea" === n,
				a = "input" === n;
			this.isMultiLine = o || !a && this._isContentEditable(this.element), this.valueMethod = this.element[o || a ? "val" : "text"], this.isNewMenu = !0, this._addClass("ui-autocomplete-input"), this.element.attr("autocomplete", "off"), this._on(this.element, {
				keydown: function(n) {
					if (this.element.prop("readOnly")) return e = !0, s = !0, void(i = !0);
					e = !1, s = !1, i = !1;
					var o = t.ui.keyCode;
					switch (n.keyCode) {
						case o.PAGE_UP:
							e = !0, this._move("previousPage", n);
							break;
						case o.PAGE_DOWN:
							e = !0, this._move("nextPage", n);
							break;
						case o.UP:
							e = !0, this._keyEvent("previous", n);
							break;
						case o.DOWN:
							e = !0, this._keyEvent("next", n);
							break;
						case o.ENTER:
							this.menu.active && (e = !0, n.preventDefault(), this.menu.select(n));
							break;
						case o.TAB:
							this.menu.active && this.menu.select(n);
							break;
						case o.ESCAPE:
							this.menu.element.is(":visible") && (this.isMultiLine || this._value(this.term), this.close(n), n.preventDefault());
							break;
						default:
							i = !0, this._searchTimeout(n)
					}
				},
				keypress: function(s) {
					if (e) return e = !1, void((!this.isMultiLine || this.menu.element.is(":visible")) && s.preventDefault());
					if (!i) {
						var n = t.ui.keyCode;
						switch (s.keyCode) {
							case n.PAGE_UP:
								this._move("previousPage", s);
								break;
							case n.PAGE_DOWN:
								this._move("nextPage", s);
								break;
							case n.UP:
								this._keyEvent("previous", s);
								break;
							case n.DOWN:
								this._keyEvent("next", s)
						}
					}
				},
				input: function(t) {
					return s ? (s = !1, void t.preventDefault()) : void this._searchTimeout(t)
				},
				focus: function() {
					this.selectedItem = null, this.previous = this._value()
				},
				blur: function(t) {
					return this.cancelBlur ? void delete this.cancelBlur : (clearTimeout(this.searching), this.close(t), void this._change(t))
				}
			}), this._initSource(), this.menu = t("<ul>").appendTo(this._appendTo()).menu({
				role: null
			}).hide().menu("instance"), this._addClass(this.menu.element, "ui-autocomplete", "ui-front"), this._on(this.menu.element, {
				mousedown: function(e) {
					e.preventDefault(), this.cancelBlur = !0, this._delay(function() {
						delete this.cancelBlur, this.element[0] !== t.ui.safeActiveElement(this.document[0]) && this.element.trigger("focus")
					})
				},
				menufocus: function(e, i) {
					var s, n;
					return this.isNewMenu && (this.isNewMenu = !1, e.originalEvent && /^mouse/.test(e.originalEvent.type)) ? (this.menu.blur(), void this.document.one("mousemove", function() {
						t(e.target).trigger(e.originalEvent)
					})) : (n = i.item.data("ui-autocomplete-item"), !1 !== this._trigger("focus", e, {
						item: n
					}) && e.originalEvent && /^key/.test(e.originalEvent.type) && this._value(n.value), s = i.item.attr("aria-label") || n.value, void(s && t.trim(s).length && (this.liveRegion.children().hide(), t("<div>").text(s).appendTo(this.liveRegion))))
				},
				menuselect: function(e, i) {
					var s = i.item.data("ui-autocomplete-item"),
						n = this.previous;
					this.element[0] !== t.ui.safeActiveElement(this.document[0]) && (this.element.trigger("focus"), this.previous = n, this._delay(function() {
						this.previous = n, this.selectedItem = s
					})), !1 !== this._trigger("select", e, {
						item: s
					}) && this._value(s.value), this.term = this._value(), this.close(e), this.selectedItem = s
				}
			}), this.liveRegion = t("<div>", {
				role: "status",
				"aria-live": "assertive",
				"aria-relevant": "additions"
			}).appendTo(this.document[0].body), this._addClass(this.liveRegion, null, "ui-helper-hidden-accessible"), this._on(this.window, {
				beforeunload: function() {
					this.element.removeAttr("autocomplete")
				}
			})
		},
		_destroy: function() {
			clearTimeout(this.searching), this.element.removeAttr("autocomplete"), this.menu.element.remove(), this.liveRegion.remove()
		},
		_setOption: function(t, e) {
			this._super(t, e), "source" === t && this._initSource(), "appendTo" === t && this.menu.element.appendTo(this._appendTo()), "disabled" === t && e && this.xhr && this.xhr.abort()
		},
		_isEventTargetInWidget: function(e) {
			var i = this.menu.element[0];
			return e.target === this.element[0] || e.target === i || t.contains(i, e.target)
		},
		_closeOnClickOutside: function(t) {
			this._isEventTargetInWidget(t) || this.close()
		},
		_appendTo: function() {
			var e = this.options.appendTo;
			return e && (e = e.jquery || e.nodeType ? t(e) : this.document.find(e).eq(0)), e && e[0] || (e = this.element.closest(".ui-front, dialog")), e.length || (e = this.document[0].body), e
		},
		_initSource: function() {
			var e, i, s = this;
			t.isArray(this.options.source) ? (e = this.options.source, this.source = function(i, s) {
				s(t.ui.autocomplete.filter(e, i.term))
			}) : "string" == typeof this.options.source ? (i = this.options.source, this.source = function(e, n) {
				s.xhr && s.xhr.abort(), s.xhr = t.ajax({
					url: i,
					data: e,
					dataType: "json",
					success: function(t) {
						n(t)
					},
					error: function() {
						n([])
					}
				})
			}) : this.source = this.options.source
		},
		_searchTimeout: function(t) {
			clearTimeout(this.searching), this.searching = this._delay(function() {
				var e = this.term === this._value(),
					i = this.menu.element.is(":visible"),
					s = t.altKey || t.ctrlKey || t.metaKey || t.shiftKey;
				(!e || e && !i && !s) && (this.selectedItem = null, this.search(null, t))
			}, this.options.delay)
		},
		search: function(t, e) {
			return t = null != t ? t : this._value(), this.term = this._value(), t.length < this.options.minLength ? this.close(e) : this._trigger("search", e) !== !1 ? this._search(t) : void 0
		},
		_search: function(t) {
			this.pending++, this._addClass("ui-autocomplete-loading"), this.cancelSearch = !1, this.source({
				term: t
			}, this._response())
		},
		_response: function() {
			var e = ++this.requestIndex;
			return t.proxy(function(t) {
				e === this.requestIndex && this.__response(t), this.pending--, this.pending || this._removeClass("ui-autocomplete-loading")
			}, this)
		},
		__response: function(t) {
			t && (t = this._normalize(t)), this._trigger("response", null, {
				content: t
			}), !this.options.disabled && t && t.length && !this.cancelSearch ? (this._suggest(t), this._trigger("open")) : this._close()
		},
		close: function(t) {
			this.cancelSearch = !0, this._close(t)
		},
		_close: function(t) {
			this._off(this.document, "mousedown"), this.menu.element.is(":visible") && (this.menu.element.hide(), this.menu.blur(), this.isNewMenu = !0, this._trigger("close", t))
		},
		_change: function(t) {
			this.previous !== this._value() && this._trigger("change", t, {
				item: this.selectedItem
			})
		},
		_normalize: function(e) {
			return e.length && e[0].label && e[0].value ? e : t.map(e, function(e) {
				return "string" == typeof e ? {
					label: e,
					value: e
				} : t.extend({}, e, {
					label: e.label || e.value,
					value: e.value || e.label
				})
			})
		},
		_suggest: function(e) {
			var i = this.menu.element.empty();
			this._renderMenu(i, e), this.isNewMenu = !0, this.menu.refresh(), i.show(), this._resizeMenu(), i.position(t.extend({
				of: this.element
			}, this.options.position)), this.options.autoFocus && this.menu.next(), this._on(this.document, {
				mousedown: "_closeOnClickOutside"
			})
		},
		_resizeMenu: function() {
			var t = this.menu.element;
			t.outerWidth(Math.max(t.width("").outerWidth() + 1, this.element.outerWidth()))
		},
		_renderMenu: function(e, i) {
			var s = this;
			t.each(i, function(t, i) {
				s._renderItemData(e, i)
			})
		},
		_renderItemData: function(t, e) {
			return this._renderItem(t, e).data("ui-autocomplete-item", e)
		},
		_renderItem: function(e, i) {
			return t("<li>").append(t("<div>").text(i.label)).appendTo(e)
		},
		_move: function(t, e) {
			return this.menu.element.is(":visible") ? this.menu.isFirstItem() && /^previous/.test(t) || this.menu.isLastItem() && /^next/.test(t) ? (this.isMultiLine || this._value(this.term), void this.menu.blur()) : void this.menu[t](e) : void this.search(null, e)
		},
		widget: function() {
			return this.menu.element
		},
		_value: function() {
			return this.valueMethod.apply(this.element, arguments)
		},
		_keyEvent: function(t, e) {
			(!this.isMultiLine || this.menu.element.is(":visible")) && (this._move(t, e), e.preventDefault())
		},
		_isContentEditable: function(t) {
			if (!t.length) return !1;
			var e = t.prop("contentEditable");
			return "inherit" === e ? this._isContentEditable(t.parent()) : "true" === e
		}
	}), t.extend(t.ui.autocomplete, {
		escapeRegex: function(t) {
			return t.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&")
		},
		filter: function(e, i) {
			var s = new RegExp(t.ui.autocomplete.escapeRegex(i), "i");
			return t.grep(e, function(t) {
				return s.test(t.label || t.value || t)
			})
		}
	}), t.widget("ui.autocomplete", t.ui.autocomplete, {
		options: {
			messages: {
				noResults: "No search results.",
				results: function(t) {
					return t + (t > 1 ? " results are" : " result is") + " available, use up and down arrow keys to navigate."
				}
			}
		},
		__response: function(e) {
			var i;
			this._superApply(arguments), this.options.disabled || this.cancelSearch || (i = e && e.length ? this.options.messages.results(e.length) : this.options.messages.noResults, this.liveRegion.children().hide(), t("<div>").text(i).appendTo(this.liveRegion))
		}
	});
	t.ui.autocomplete
});
var cbSuggestions = $.noop;  //dummy callback for JSONP
$(function() {
	var noResultsMsg = 'Just click enter when you are ready',
	currentDomain = window.location.protocol + "//" + location.hostname;
	//for local use comment below 2 lines.
	var sKeyword = '';
	//Session.set('keyword','');
	$('#keyword').val(sKeyword);
	function updateOmniture(term, key) {
		shc.clickTracking('Search', key, term);
	}
	// To encode the special characters
	function encode(s) {
		s = encodeURIComponent(s);
		return s.replaceAll('.', '%2E').replaceAll('-', '%2D').replaceAll('*', '%2A').replaceAll('_', '%5F'); // ECOM-264604
	}
	// To encode the search key word
	function encodeSearchKeyWord(s) {
		s = s.replaceAll('%', '%25');
		return encode(s).replaceAll('%3F', '%253F').replaceAll('%2B', '%252B'); //ECOM-285820 and ECOM-297657
	}
	/**
	 * Convert the input to lowercase, replace & with AND.
	 * trim any spaces in the input e.g. vanity fair will be trimmed to vanityfair
	 * Add stearn to replace stearns so that it can match with the brand map.
	 *  Autofill suggestions suggest 'stearn and foster' instead of 'stearns and foster'  hence the pattern never matches.
	 * @param s - input from Autofill suggestions and the Brand Map
	 * @returns {string}
	 */
	var formatInput = function(s) {
		//convert the input to lower case and remove the spaces
		return s.toLowerCase().replace(/&/g, ' and ').replace(/stearn /g, 'stearns ').replace(/ /g, '');
	};
	function doSearch(keyword, levels) {
		var parms;
		keyword = encodeSearchKeyWord(keyword);
		if (!!levels) {
			if (levels === 'All Departments') {
                levels = encodeSearchKeyWord("All Verticals") + "&catPrediction=0&redirect=0&searchBy=keyword";
			} else {
                levels = encodeSearchKeyWord(levels) + "&redirect=0&disabledDiscoveryFlow=true&searchBy=keyword";
			}
		   parms = keyword + '?levels=' + levels;
		} else {
			parms = keyword;
		}
		//for local use replace all $.q('#keyword') with $('#keyword')
		setTimeout(function() {
			//for local use comment below line.
			//Session.set('keyword', $('#keyword').val());
			window.location = '/search=' + parms; //attempt to fix omniture issue - UHF-932
		}, 200);
	}
	function getBrandOverviewObject(siteName) {
		var brandOverview = [];
		if (siteName === 'kmart') {
			brandOverview = [
				{'brandName': 'Farberware ' ,'link': '/en_us/dap/farberware-brand-showcase.html'},
				{'brandName': 'Aqua Chem' ,'link': '/en_us/dap/aqua-chem.html'}
			];
		} else if (siteName === 'sears') {
			brandOverview = [
				{'brandName': 'Bosch' ,'link': '/en_us/dap/bosch.html'},
				{'brandName': 'Kitchen Aid' ,'link': '/en_us/dap/kitchenaid.html'},
				{'brandName': 'Maytag' ,'link': '/en_us/dap/maytag-brand-showcase.html'},
				{'brandName': 'LG Laundry' ,'link': '/content/shc/sears/en_us/dap/lg_laundry.html'},
				{'brandName': 'Frigidaire' ,'link': '/en_us/dap/frigidaire-appliances.html'},
				{'brandName': 'Calphalon' ,'link': '/en_us/dap/calphalon-brand-showcase-B.html'},
				{'brandName': 'Rachel Ray' ,'link': '/content/shc/sears/en_us/dap/Rachael_Ray.html'},
				{'brandName': 'Serta' ,'link': '/en_us/dap/serta-mattresses.html'},
				{'brandName': 'Sealy' ,'link': '/en_us/dap/sealy.html'},
				{'brandName': 'Tempur Pedic' ,'link': '/en_us/dap/tempur-pedic.html'},
				{'brandName': 'Stearns & Foster' ,'link': '/en_us/dap/stearns-and-foster.html'},
				{'brandName': 'Hanes' ,'link': '/en_us/dap/Hanes.html'},
				{'brandName': 'Vanity Fair' ,'link': '/en_us/dap/vanity-fair-brand-showcase.html'},
				{'brandName': 'Michelin' ,'link': '/en_us/dap/MichelinTires.html'},
				{'brandName': 'Whirlpool' ,'link': '/en_us/dap/whirlpool.html'}
			];
		}
		return brandOverview;
	}
	// Method to determine the current autofill site
	var getAutofillSite = function() {
	var host = document.getElementById('sitesradio').value,
                result;
                if (document.getElementById('r1').checked) {
                result = 'sears';
                }
                if (document.getElementById('r2').checked) {
                 result = 'kmart';
                }
                if (document.getElementById('r3').checked) {
                result = 'mygofer';
                }
                return result;
	//	var host = location.hostname,
	//		result;
	//	if (host.match('kmart')) {
	//		if (host.match('m.kmart')) {
	//			result = host.startsWith('m.kmart.com') ? 'mkmart' : 'kmart';
	//		} else {
	//			result = 'kmart';
	//		}
	//	} else if (host.match('mygofer')) {
	//		result = 'mygofer';
	//	} else if (host.match('searspr') || host.match('sears.com.pr')) {
	//		result = 'searspr';
	//	} else if (host.match('m.sears')) {
	//		result = host.startsWith('m.sears.com') ? 'msears' : 'sears';
	//	} else { //This is for local testing
		//	result = window.location.href.indexOf('kmart') !== -1 ? 'kmart' : 'sears';
		//}
		//return result;
	};
	//AutofillComponent class start
	var AutofillComponent = function(serviceUrl, colorHighlight) {
		this.term = "";
		this.vtSize=0;
		this.turnOffCategory = true;
		//this.turnOffCategory = window.shc ? shc.features.autofillCategoryOff : true;
		this.foundCategory = false;
		this.serviceUrl = serviceUrl;
		this.datalist = [];
        	this.categories = [];
		this.spellcorrectedWords = [];
		this.color = colorHighlight;
		this.siteName = getAutofillSite();
		this.brandOverviewObject = getBrandOverviewObject(getAutofillSite());
	};
	//AutofillComponent function to get suggestions dynamically using ajax call to autofill service
	AutofillComponent.prototype.getSuggestions = function(keyword) {
		return $.ajax({
			url: this.serviceUrl,
			dataType: 'jsonp',
			cache: true,
			jsonpCallback: 'cbSuggestions',
			data: {
				q: keyword,
				site: getAutofillSite()
			}
		});
	};
	//AutofillComponent function to formatSuggestions as per the site
	AutofillComponent.prototype.formatSuggestions = function(data) {
		var recommendationsCount = 0,
			brandOverviewtoAdd = null,
			s, temp,
			showBrandOverview = null,
			suggestion, jsonSuggestion,
			omnitureIndexValue = "Autofill > Selected > Category > POS";
		autoFillComponent.spellcorrectedWords = [];
		if (!$.isEmptyObject(data)) {
			if (!!data.items && data.items !== null) {
                autoFillComponent.addCategories(data);
                $.each(data.items, function(keyindex, keywords) {
                    if (!!keywords.sc && !!keywords.k && keywords.sc.length > 0) {
                        for (var s = 0; s < keywords.sc.length; s++) {
                            var temp = keywords.k.split(' ');
                            if ($.inArray(temp[keywords.sc[s]], autoFillComponent.spellcorrectedWords) == -1) {
                                autoFillComponent.spellcorrectedWords.push(temp[keywords.sc[s]]);
                            }
                        }
                    }
                    if (!!keywords.k) {
						suggestion = keywords.k;
						if (!autoFillComponent.turnOffCategory && autoFillComponent.vtSize > 1 && keyindex < 1) {
							for (var s = 0; s < autoFillComponent.vtSize; s++) {
								jsonSuggestion = {
									k: suggestion.toLowerCase(),
									label: suggestion.toLowerCase(),
									category: autoFillComponent.categories[s],
									omniturelabel: autoFillComponent.term.toLowerCase() + " in " + autoFillComponent.categories[s],
									omnitureIndex: omnitureIndexValue + (recommendationsCount + 1)
								};
								autoFillComponent.datalist.push(jsonSuggestion);
								recommendationsCount++;
							}
						} else {
							omnitureIndexValue = "Autofill > Selected > Item > POS";
							showBrandOverview = populateBrandOverview(keywords.k.toLowerCase());
							if (!brandOverviewtoAdd && !!showBrandOverview) {
								brandOverviewtoAdd = showBrandOverview;
							}
							if (!!showBrandOverview) {
								omnitureIndexValue = "Autofill > Selected > Brand > POS";
							}
							jsonSuggestion = {
								label: suggestion.toLowerCase(),
								omniturelabel: suggestion.toLowerCase(),
								omnitureIndex: omnitureIndexValue + (recommendationsCount + 1)
							};
							autoFillComponent.datalist.push(jsonSuggestion);
							recommendationsCount++;
						}                        
                    }
                    if (recommendationsCount > 9 || (!autoFillComponent.vtSize && data.items.length === recommendationsCount) ||
                        (autoFillComponent.vtSize > 1 && ((data.items.length + 2) === recommendationsCount)) ||
                        (autoFillComponent.vtSize === 1 && ((data.items.length + 1) === recommendationsCount))) {
                        if (!brandOverviewtoAdd) {
                            brandOverviewtoAdd = populateBrandOverview(autoFillComponent.term);
                        }
                        if (!!brandOverviewtoAdd) {
                            jsonSuggestion = {
                                label: brandOverviewtoAdd.brandName + " Brand Overview",
                                brandurl: currentDomain + brandOverviewtoAdd.link,
                                omniturelabel: brandOverviewtoAdd.brandName + " Brand Overview",
                                omnitureIndex: "Autofill > Selected > Brand > POS" + (recommendationsCount + 1)
                            };
                            autoFillComponent.datalist.push(jsonSuggestion);
                        }
                        return false;
					}
				});
			}
			data = {};
		} else {
			autoFillComponent.datalist.push({
				label: noResultsMsg
			});
		}
	};
    //This is for adding categories to suggestions
    AutofillComponent.prototype.addCategories = function(data) {
            if (!!data.items && !!data.items[0].vt && !autoFillComponent.turnOffCategory) {
                autoFillComponent.categories = data.items[0].vt;
                autoFillComponent.categories.unshift("All Departments");
                autoFillComponent.vtSize = data.items[0].vt.length;
            }
        }
	//create autofill object
	var	serviceURL = "http://solrx4119p.stag.ch4.s.com:9280/autofillredesign/search?",
		autoFillComponent = new AutofillComponent(serviceURL, getAutofillSite() === 'kmart' ? 'color:#cc1414' : 'color:#005ccc');
	/**
	 * To match brands with the input terms
	 */
	var populateBrandOverview = function(term) {
		var temp = term.split(' '),
			i, s, brand,
			boo = autoFillComponent.brandOverviewObject,
			len = boo.length,
			fmtTerm = formatInput(term);
		for (i = 0; i < len; i++) {
			brand = boo[i];
			if (!!fmtTerm.match(formatInput(brand.brandName))) {
				return boo[i];
			}
		}
		//no point in doing this again if single word failed above
		if (temp.length > 1) {
			for (s = 0; s < temp.length; s++) {
				fmtTerm = formatInput(temp[s]);
				for (i = 0; i < len; i++) {
					brand = boo[i];
					if (!!fmtTerm.match(formatInput(brand.brandName))) {
						return boo[i];
					}
				}
			}
		}
		return null;
	};
	//Brand overview component end
	//JQuery autocomplete component
    $('#keyword').autocomplete({
		//delay: 0,
		source: function(request, response) {
			autoFillComponent.term = request.term.toLowerCase().replace(/\s+/g, '_').trim();
			autoFillComponent.datalist = [];
			autoFillComponent.foundCategory = "";
			var keyword = autoFillComponent.term;
			var reg = new RegExp("\\d+([/])\\d+","gi");
			var fractionToken;
			//while (fractionToken = reg.exec(keyword)) {
			//	keyword = keyword.replace(fractionToken[0], fractionToken[0].replaceAll('/','by'));
			//}
			if (keyword !== "") {
				autoFillComponent.getSuggestions(keyword)
				.done(function(data) {
					autoFillComponent.formatSuggestions(data);
					response(autoFillComponent.datalist);
					autoFillComponent.datalist = [];
				})
				.fail(function(jqXHR, exception) {
					var msg = '';
					if (jqXHR.status === 0) {
						msg = 'Not connected. Verify Network.';
					} else if (jqXHR.status == 404) {
						msg = 'Requested page not found. [404]';
					} else if (jqXHR.status == 500) {
						msg = 'Internal Server Error [500].';
					} else if (exception === 'parsererror') {
						msg = 'Requested JSON parse failed.';
					} else if (exception === 'timeout') {
						msg = 'Time out error.';
					} else if (exception === 'abort') {
						msg = 'Ajax request aborted.';
					} else {
						msg = 'Uncaught Error.' + jqXHR.responseText;
					}
					console.log(msg);
					autoFillComponent.formatSuggestions({});
					response(autoFillComponent.datalist);
					autoFillComponent.datalist = [];
				});
			} else {
				response(autoFillComponent.datalist);
			}
		},
		open: function() {
            $("#ui-id-1").width($('#keyword').innerWidth());
			$('.ui-autocomplete').off('menufocus');
		},
		focus: function(e, ui) {
			e.preventDefault();
			if (ui.item.label !== noResultsMsg) {
				this.value = ui.item.label;
			} else {
				this.value = autoFillComponent.term;
			}
		},
		select: function(e, ui) {
			e.preventDefault();
			updateOmniture(ui.item.omniturelabel, ui.item.omnitureIndex);
			if (!!ui.item.brandurl) {
				setTimeout(function() {
					window.location = ui.item.brandurl;
				}, 200);
				return false;
			}
			else {
				$(this).val(ui.item.value);
			}
			if (ui.item.label !== noResultsMsg) {
				doSearch($('#keyword').val(), ui.item.category || '');
			} else {
                $('#keyword').val(autoFillComponent.term);
			}
		}
	}).keypress(function(e) {
		var val = $('#keyword').val();
		if (e.keyCode === 13) {
			e.preventDefault();
			if (val === '' || val === noResultsMsg) {
				$('#keyword').val(autoFillComponent.term);
				return false;
			}
			updateOmniture(val, 'Autofill > EnterKey');
			doSearch(val, '');
			$(this).autocomplete('close');
		}
	}).data("ui-autocomplete")._renderItem = function(ul, item) {
		var srchTerm = $.trim($('#keyword').val().replace(/[-[\]{}()*+?.,\\^$|#]/g, "\\$&")).split(/\s+/);
		if (autoFillComponent.spellcorrectedWords.length) {
			srchTerm = srchTerm.concat(autoFillComponent.spellcorrectedWords);
		}
		srchTerm = srchTerm.map(function(el) {
			if (el && el.length === 1 && "\"#$%&'()*+-./;<=>?@\]^_`|{}~,".indexOf(el) !== -1) {
				switch (true) {
					case (el === "!"):
						el = "\\x21";
						break;
					case (el === "\""):
						el = "\\x22";
						break;
					case (el === "#"):
						el = "\\x23";
						break;
					case (el === "$"):
						el = "\\x24";
						break;
					case (el === "%"):
						el = "\\x25";
						break;
					case (el === "&"):
						el = "\\x26";
						break;
					case (el === "'"):
						el = "\\x27";
						break;
					case (el === "("):
						el = "\\x28";
						break;
					case (el === ")"):
						el = "\\x29";
						break;
					case (el === "*"):
						el = "\\x2A";
						break;
					case (el === "+"):
						el = "\\x2B";
						break;
					case (el === ","):
						el = "\\x2C";
						break;
					case (el === "-"):
						el = "\\x2D";
						break;
					case (el === "."):
						el = "\\x2E";
						break;
					case (el === "/"):
						el = "\\x2F";
						break;
					case (el === ":"):
						el = "\\x3A";
						break;
					case (el === ";"):
						el = "\\x3B";
						break;
					case (el === "<"):
						el = "\\x3C";
						break;
					case (el === "="):
						el = "\\x3D";
						break;
					case (el === ">"):
						el = "\\x3E";
						break;
					case (el === "?"):
						el = "\\x3F";
						break;
					case (el === "@"):
						el = "\\x40";
						break;
					case (el === "\\"):
						el = "\\x5C";
						break;
					case (el === "]"):
						el = "\\x5D";
						break;
					case (el === "^"):
						el = "\\x5E";
						break;
					case (el === "_"):
						el = "\\x5F";
						break;
					case (el === "`"):
						el = "\\x60";
						break;
					case (el === "~"):
						el = "\\x7E";
						break;
					case (el === "|"):
						el = "\\x7C";
						break;
					case (el === "}"):
						el = "\\x7D";
						break;
					case (el === "{"):
						el = "\\x7B";
						break;
				}
			} else if (el) {
				el = '\\b' + el;
			}
			return el;
		});
		srchTerm = srchTerm.join('|');
		var re = new RegExp('(' + srchTerm + ')', 'ig'),
			t = '';
		if (!!item.category && !autoFillComponent.turnOffCategory) {
			t = item.label.replace(re, "<span style='color:#666;font-weight:400;'>$&</span>") + " in <span style='" + autoFillComponent.color + "'>" + item.category + "</span>";
			autoFillComponent.foundCategory = "yes";
		} else if (!!item.brandurl) {
			t = "<span style='" + autoFillComponent.color + "'>" + item.label + "</span>";
			if (autoFillComponent.foundCategory == "yes") {
				autoFillComponent.foundCategory = "no";
			}
		} else {
			t = item.label.replace(re, "<span style='color:#666;font-weight:400;'>$&</span>");
			if (autoFillComponent.foundCategory == "yes") {
				autoFillComponent.foundCategory = "no";
			}
		}
		var listItem = $('<li></li>')
			.data('ui-autocomplete-item', item)
			.append('<div>' + t + '</div>')
			.appendTo(ul);
		if (autoFillComponent.foundCategory == 'no') {
			listItem.addClass('separator');
			autoFillComponent.foundCategory = '';
		}
		if (item.label.indexOf(noResultsMsg) !== -1) {
			listItem.addClass('no-items');
		}
		return listItem;
	};
	$('#goBtn').click(function(e) {
		e.preventDefault();
		var val = $('#keyword').val();
		//Do nothing if no term entered
		if (!val || val === noResultsMsg) {
			$('#keyword').val(autoFillComponent.term);
			return false;
		}
		updateOmniture(val, 'Autofill > ButtonClick');
		doSearch(val, '');
	});
	setTimeout(function() {
		$('#keyword').autocomplete('widget').on('mouseleave', function() {
			$('#keyword').autocomplete('close');
		});
	}, 200);
});
