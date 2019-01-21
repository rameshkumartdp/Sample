    /*
     String Extensions
       This is the preferred way to do string manipulation, rather than global functions.
       They all check for existence first so that in case a specific browser has implemented the function in the JS engine,
       these versions will be ignored, as it's assumed that the browser version will be more efficient.
    */
    if (!String.prototype.replaceAll) {
        /**
 * @member String
	 * Replace all instances of one string within another string.
	//Example
	' foobar'.replaceAll('o', '-');		// returns "f--bar"
	'fo o b a r'.replaceAll(' ', '');	// returns "foobar"
	 * @param {String} find the string to find.
	 * @param {String} replace the string to replace with.
 * @return {String} the modified string.
	 */
        String.prototype.replaceAll = function(find, replace) {
            return this.split(find).join(replace);
        };
    }
    if (!String.prototype.trim) {
        /**
 * @member String
	 * Return string with leading and trailing spaces removed.
	//Example
' foobar'.trim();	// returns 'foobar'
'foobar '.trim();	// returns 'foobar'
' foobar '.trim();	// returns 'foobar'
'foo bar'.trim();	// returns 'foo bar'
 * @return {String} the trimmed string.
	 */
        String.prototype.trim = function() {
            return this.replace(/^\s+|\s+$/g, '');
        };
    }
    if (!String.prototype.ltrim) {
        /**
 * @member String
	 * Return string with leading spaces removed.
	//Example
' foobar'.ltrim();	// returns 'foobar'
' foobar '.ltrim();	// returns 'foobar '
'foo bar'.ltrim();	// returns 'foo bar'
 * @return {String} the trimmed string.
	 */
        String.prototype.ltrim = function() {
            return this.replace(/^\s+/, '');
        };
    }
    if (!String.prototype.rtrim) {
        /**
 * @member String
	 * Return string with trailing spaces removed.
	//Example
'foobar '.rtrim();	// returns 'foobar'
' foobar '.rtrim();	// returns ' foobar'
'foobar'.rtrim();	// returns 'foobar'
 * @return {String} the trimmed string.
	 */
        String.prototype.rtrim = function() {
            return this.replace(/\s+$/, '');
        };
    }
    if (!String.prototype.mtrim) {
        /**
 * @member String
	 * Return string with all multiple (two or more) spaces reduced to single spaces.
	//Example
'foo bar'.mtrim();				// returns 'foo bar'
'foo     bar'.mtrim();			// returns 'foo bar'
'foo  bar  baz'.mtrim();		// returns 'foobarbaz'
'foobar'.mtrim();				// returns 'foobar'
 * @return {String} the trimmed string.
	 */
        String.prototype.mtrim = function() {
            return this.replace(/\s{2,}/g, ' ');
        };
    }
    if (!String.prototype.startsWith) {
        /**
 * @member String
	 * Determine whether the string starts with a passed string.
	//Example
'foobar'.startsWith('foo')  //true
'foobar'.startsWith('abc')	//false
 * @param {String} string the substring to check.
 * @return {Boolean} true if this string starts with the passed string.
	 */
        String.prototype.startsWith = function(s) {
            return this.match('^' + s) == s; //array[0] casts to string
        };
    }
    if (!String.prototype.endsWith) {
        /**
 * @member String
	 * Determine whether the string ends with a passed string.
	//Example
'foobar'.endsWith('bar')  	//true
'foobar'.endsWith('abc')	//false
 * @param {String} string the substring to check.
 * @return {Boolean} true if this string ends with the passed string.
	 */
        String.prototype.endsWith = function(s) {
            return this.match(s + '$') == s; //array[0] casts to string
        };
    }
    /**
     * @static
     * @member String
     * Format a string by replacing an arbitrary number of tokens with passed values.
     * The tokens must be unique and must increment in the format {0}, {1}, etc.
    	//Example
    	var s = String.format('The {0} red {1} jumped over', 'lazy', 'fox');
    	// s now contains the string: 'The lazy red fox jumped over'
     * @param {String} string the string to be formatted
     * @param {String} value1 value to replace token {0}
     * @param {String} value2 value to replace token {1}, etc..
     * @return {String} the formatted string
     */
    String.format = function(format) {
        var args = Array.prototype.slice.call(arguments, 1);
        return format.replace(/\{(\d+)\}/g, function(m, i) {
            return args[i];
        });
    };
    /* End String Extensions */
    // Responsible for trimming extra spaces leading, trailling and middle
    $.trimExtraSpaces = function(s) {
        return s.mtrim().trim();
    };
